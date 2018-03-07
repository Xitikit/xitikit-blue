package org.xitikit.blue.b2c.v2dot0.authentication;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.InvalidSignatureException;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.xitikit.blue.b2c.v2dot0.policy.B2cPolicyUrlService;
import org.xitikit.blue.common.services.nonce.NonceService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.time.ZonedDateTime;

import static org.xitikit.blue.b2c.v2dot0.authentication.VerificationUtil.*;

/**
 * This class implements methods to interface with AzureB2C.
 *
 * @author J. Keith Hoopes
 * @see B2CAuthenticationService
 */
public final class SimpleB2CAuthenticationService implements B2CAuthenticationService{

    private static final Logger log = LoggerFactory.getLogger(SimpleB2CAuthenticationService.class);

    private final ClaimValidationService claimValidationService;

    private final NonceService nonceService;

    private final B2cPolicyUrlService urlService;

    private final RestTemplate restTemplate;

    public SimpleB2CAuthenticationService(
        final ClaimValidationService claimValidationService,
        final NonceService nonceService,
        final B2cPolicyUrlService urlService,
        final RestTemplate restTemplate){

        Assert.notNull(
            claimValidationService,
            "Missing required parameter 'claimValidationService' " +
                "(org.xitikit.blue.noitacitnehtua.api.v2dot0.SimpleB2CAuthenticationService::new)");
        Assert.notNull(nonceService, "Missing required parameter 'nonceService' (org.xitikit.blue.noitacitnehtua.api.v2dot0.SimpleB2CAuthenticationService::new)");
        Assert.notNull(urlService, "Missing required parameter 'blueUrlService' (org.xitikit.blue.noitacitnehtua.api.v2dot0.SimpleB2CAuthenticationService::new)");
        Assert.notNull(restTemplate, "Missing required parameter 'restTemplate' (org.xitikit.blue.noitacitnehtua.api.v2dot0.SimpleB2CAuthenticationService::new)");

        this.claimValidationService = claimValidationService;
        this.nonceService = nonceService;
        this.urlService = urlService;
        this.restTemplate = restTemplate;
    }

    @Nullable
    @Override
    public BlueWebToken decodeAndVerify(@Nonnull final String idToken){
        //TODO: This method is too long. Break it down into smaller private methods, or possibly a util class
        long now = System.currentTimeMillis();
        if(log.isTraceEnabled()){
            log.trace("Decoding token [" + idToken + "]");
        }
        try{

            Jwt jwt = JwtHelper.decode(idToken);
            // Get the key ID we need to use to verify the token
            String keyId = getKeyId(idToken);
            if("".equals(keyId.trim())){
                log.warn("Failed to retrieve key ID for token");
                return null;
            }
            BlueWebToken token = typeSecuredObjectMapper()
                .readValue(
                    jwt.getClaims(),
                    BlueWebToken.class);
            if(token == null){
                log.warn("The authentication token from Azure could not be parsed.");
                return null;
            }
            // Get the key and verify the JWT signature
            RSAPublicKey key = rsaPublicKey(keyId, token.getAuthContextReference());
            jwt.verifySignature(new RsaVerifier(key));

            // Validate the nonce

            if(!nonceService.isValid(token.getNonce())){
                log.warn("Failed to validate nonce in token. This could be a replay attack.");
                return null;
            }
            if(!claimValidationService.validateAudience(token)){
                log.warn("Failed to validate audience in token. This could be a replay attack.");
                return null;
            }
            if(!claimValidationService.validateIssuer(token)){
                log.warn("Failed to validate issuer of token. This could be a replay attack.");
                return null;
            }
            if(!claimValidationService.validateNotBefore(token, now)){
                ZonedDateTime notBefore = token.getNotBefore();
                Instant value = notBefore == null ? null : notBefore.toInstant();
                log.warn("" +
                    "Failed to validate notBefore time in token. " +
                    "This could be a replay attack. " +
                    "'Now' milliseconds: " + now + "; " +
                    "'NotBefore' milliseconds: " + (value == null ? "null" : "" + value.toEpochMilli()));
                return null;
            }
            if(!claimValidationService.validateExpiration(token, now)){
                ZonedDateTime notBefore = token.getNotBefore();
                Instant value = notBefore == null ? null : notBefore.toInstant();
                log.warn("Failed to validate expiration time in token. " +
                    "This could be a replay attack. " +
                    "'Now' milliseconds: " + now + "; " +
                    "'Expiration' milliseconds: " + (
                    value == null
                        ? "null"
                        : "" + value.toEpochMilli()));
                return null;
            }

            return token;

        }catch(IOException | IllegalArgumentException | InvalidSignatureException x){
            log.warn("Failed to extract data from JWT token: " + x.getMessage(), x);
        }
        return null;
    }

    /**
     * Attempts to get an RSAPublicKey for the given policy.
     *
     * @param keyId the key ID to fetch
     * @param policy the policy the key belongs to
     *
     * @return the key or null if it could not be fetched
     */
    @Nullable
    private RSAPublicKey rsaPublicKey(
        final String keyId,
        final String policy){

        if(keyId == null || policy == null){
            return null;
        }
        try{
            JsonNode kidNode = kidForKeyId(
                keysForPolicy(policy),
                keyId
            );
            return parseKey(
                modulus(kidNode),
                exponent(kidNode)
            );

        }catch(RestClientException | NullPointerException x){
            log.error("Error retrieving RSA keys for policy [" + policy + "]: " + x.getMessage(), x);
        }
        return null;
    }

    @Nonnull
    private JsonNode keysForPolicy(@Nonnull final String policy){

        return safeNode(
            jwksForPolicy(policy).get("keys")
        );
    }

    @Nonnull
    private JsonNode jwksForPolicy(@Nonnull final String policy){

        return safeNode(
            restTemplate.getForObject(
                jwksUri(
                    policyMetaData(policy)
                ),
                JsonNode.class
            ));
    }

    @Nonnull
    private JsonNode policyMetaData(@Nonnull final String policy){

        return safeNode(
            restTemplate.getForObject(
                urlService.wellKnownEndpoint(policy),
                JsonNode.class
            ));
    }
}
