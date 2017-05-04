package org.xitikit.blue.openidconnect;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.InvalidSignatureException;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.xitikit.blue.config.B2CProperties;
import org.xitikit.blue.config.NonceProperties;
import org.xitikit.blue.config.PolicyProperties;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Date;

/**
 * This class implements methods to interface with AzureB2C.
 *
 * @author J. Keith Hoopes
 * @see B2CService
 */
@Slf4j
public final class SimpleB2CService implements B2CService {

    private static final Charset UTF8 = Charset.forName("UTF-8");
    private static final String STANDARD_CONFIGURATION_QUERY = "&response_mode=form_post&scope=openid&response_type=id_token&prompt=login";

    private B2CProperties b2CProperties;
    private PolicyProperties signUpPolicy;
    private PolicyProperties signInPolicy;
    private PolicyProperties signUpOrSignInPolicy;
    private PolicyProperties resetPasswordPolicy;
    private PolicyProperties editProfilePolicy;
    private PolicyProperties signOutPolicy;
    private NonceProperties nonceProperties;
    private NonceService nonceService;
    private ObjectMapper mapper = new ObjectMapper();

    public SimpleB2CService(B2CProperties b2CProperties, PolicyProperties signUpPolicy, PolicyProperties signInPolicy, PolicyProperties signUpOrSignInPolicy, PolicyProperties resetPasswordPolicy, PolicyProperties editProfilePolicy, PolicyProperties signOutPolicy, NonceProperties nonceProperties, NonceService nonceService, ObjectMapper mapper) {
        this.b2CProperties = b2CProperties;
        this.signUpPolicy = signUpPolicy;
        this.signInPolicy = signInPolicy;
        this.signUpOrSignInPolicy = signUpOrSignInPolicy;
        this.resetPasswordPolicy = resetPasswordPolicy;
        this.editProfilePolicy = editProfilePolicy;
        this.signOutPolicy = signOutPolicy;
        this.nonceProperties = nonceProperties;
        this.nonceService = nonceService;
        this.mapper = mapper;
    }

    private String domainUrlPart() {
        return "https://login.microsoftonline.com/" + b2CProperties.getDomain();
    }

    private String policyUrlPart(PolicyProperties policy) {
        assert policy != null;
        return "/oauth2/v2.0/authorize?p=" + policy.getName();
    }

    private String clientIdUrlPart() {
        return "&client_Id=" + b2CProperties.getAppId();
    }

    private String nonceUrlPart() {
        return (nonceProperties.isEnabled() ? "&nonce=" + nonceService.generate() : "");
    }

    private String redirectUrlPart(PolicyProperties policy) {
        assert policy != null;
        return "&redirect_uri=" + signUpPolicy.getRedirect();
    }

    @Nonnull
    @Override
    public String getSignUpUrl() {

        return domainUrlPart() +
                policyUrlPart(signUpPolicy) +
                clientIdUrlPart() +
                nonceUrlPart() +
                redirectUrlPart(signUpPolicy) + STANDARD_CONFIGURATION_QUERY;
    }

    @Nonnull
    @Override
    public String getSignInUrl() {

        return domainUrlPart() +
                policyUrlPart(signInPolicy) +
                clientIdUrlPart() +
                nonceUrlPart() +
                redirectUrlPart(signInPolicy) +
                STANDARD_CONFIGURATION_QUERY;
    }

    @Nonnull
    @Override
    public String getSignUpOrSignInUrl() {

        return domainUrlPart() +
                policyUrlPart(signUpOrSignInPolicy) +
                clientIdUrlPart() +
                nonceUrlPart() +
                redirectUrlPart(signUpOrSignInPolicy) + STANDARD_CONFIGURATION_QUERY;
    }

    @Nonnull
    @Override
    public String getSignOutUrl() {

        return domainUrlPart() +
                policyUrlPart(signOutPolicy) +
                "&post_logout_redirect_uri=" + signOutPolicy.getRedirect() +
                nonceUrlPart();
    }

    @Nonnull
    @Override
    public String getProfileUrl() {

        return domainUrlPart() +
                policyUrlPart(editProfilePolicy) +
                clientIdUrlPart() +
                nonceUrlPart() +
                redirectUrlPart(editProfilePolicy) +
                STANDARD_CONFIGURATION_QUERY;
    }

    @Nonnull
    @Override
    public String getResetPasswordUrl() {

        return domainUrlPart() +
                policyUrlPart(resetPasswordPolicy) +
                clientIdUrlPart() +
                nonceUrlPart() +
                redirectUrlPart(resetPasswordPolicy) +
                STANDARD_CONFIGURATION_QUERY;
    }

    @Nullable
    @Override
    public BlueWebToken decodeAndVerify(@Nonnull String idToken) {
        final Date now = new Date();
        if (log.isTraceEnabled()) {
            log.trace("Decoding token [" + idToken + "]");
        }
        try {
            Jwt jwt = JwtHelper.decode(idToken);
            String claims = jwt.getClaims();
            BlueWebToken token = mapper.readValue(claims, BlueWebToken.class);
            // Validate the nonce
            if (!nonceService.isValid(token.getNonce())) {
                log.warn("Failed to validate nonce in token. This could be a replay attack.");
                return null;
            }
            if (!validateAudience(token)) {
                log.warn("Failed to validate audience in token. This could be a replay attack.");
                return null;
            }
            if (!validateIssuer(token)) {
                log.warn("Failed to validate issuer of token. This could be a replay attack.");
                return null;
            }
            if (!validateNotBefore(token, now)) {
                log.warn("Failed to validate notBefore time in token. This could be a replay attack. 'Now' milliseconds: " + now.getTime() + "; 'NotBefore' milliseconds: " + token.getNotBefore().getTime());
                return null;
            }
            if (!validateExpiration(token, now)) {
                log.warn("Failed to validate expiration time in token. This could be a replay attack. 'Now' milliseconds: " + now.getTime() + "; 'Expiration' milliseconds: " + token.getExpiration().getTime());
                return null;
            }
            // Get the key ID we need to use to verify the token
            String keyId = getKeyId(idToken);
            if (keyId == null) {
                log.warn("Failed to retrieve key ID for token");
                return null;
            }
            // Get the key and verify the JWT signature
            RSAPublicKey key = getKey(keyId, token.getAuthContextReference());
            jwt.verifySignature(new RsaVerifier(key));
            return token;

        } catch (IOException | IllegalArgumentException | InvalidSignatureException x) {
            log.warn("Failed to extract data from JWT token: " + x.getMessage(), x);
        }
        return null;
    }

    private boolean validateAudience(BlueWebToken token) {

        return b2CProperties.getAppId().equals(token.getAudience());
    }

    private boolean validateIssuer(BlueWebToken token) {

        final String issuer = token.getIssuer();
        return issuer != null &&
                issuer.startsWith("https://login.microsoftonline.com/") &&
                issuer.endsWith("/v2.0/");
    }

    /**
     * Attempts to retrieve the key ID from the JWT token header.
     *
     * @param idToken the JWT token
     * @return the key ID or null
     */
    @Nullable
    private String getKeyId(@Nonnull String idToken) {

        try {
            String[] parts = idToken.split("\\.");
            byte[] bytes = Base64.decodeBase64(parts[0]);
            String json = new String(bytes, UTF8);
            JsonNode node = mapper.readValue(json, JsonNode.class);
            if (node.has("kid")) {
                return node.get("kid").asText();
            }
        } catch (IOException x) {
            log.warn("Failed to parse jwt token header: " + x.getMessage(), x);
        }
        return null;
    }

    /**
     * Attempts to get an RSAPublicKey for the given policy.
     *
     * @param keyId  the key ID to fetch
     * @param policy the policy the key belongs to
     * @return the key or null if it could not be fetched
     */
    @Nullable
    private RSAPublicKey getKey(@Nonnull String keyId, @Nonnull String policy) {

        RestTemplate restTemplate = new RestTemplate();
        try {
            // Get the meta data
            String url = domainUrlPart() + "/v2.0/.well-known/openid-configuration?p=" + policy;
            JsonNode node = restTemplate.getForObject(url, JsonNode.class);

            // Get keys
            url = node.get("jwks_uri").asText();
            node = restTemplate.getForObject(url, JsonNode.class);
            node = node.get("keys");

            for (JsonNode keyNode : node) {
                if (keyNode.get("kid").asText().equals(keyId)) {
                    return parseKey(keyNode.get("n").asText(), keyNode.get("e").asText());
                }
            }

        } catch (RestClientException | NullPointerException x) {
            log.error("Error retrieving RSA keys for policy [" + policy + "]: " + x.getMessage(), x);
        }
        return null;
    }

    /**
     * Accepts a modulus and exponent base64 encoded string and produces an RSAPublicKey.
     *
     * @param n the base64 encoded modulus
     * @param e the base64 encoded exponent
     * @return the key or null if it could not be created
     */
    @Nullable
    private RSAPublicKey parseKey(@Nonnull String n, @Nonnull String e) {
        try {
            byte[] modulus = Base64.decodeBase64(n);
            byte[] exponent = Base64.decodeBase64(e);
            RSAPublicKeySpec spec = new RSAPublicKeySpec(
                    new BigInteger(1, modulus),
                    new BigInteger(1, exponent));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (RSAPublicKey) keyFactory.generatePublic(spec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException x) {
            log.error("Failed to parse RSA public key: " + x.getMessage(), x);
        }
        return null;
    }

    private boolean validateExpiration(BlueWebToken token, Date now) {

        return token.getExpiration().compareTo(now) > 0;
    }

    @SuppressWarnings("unused")
    private boolean validateNotBefore(BlueWebToken token, Date now) {

        Date nowWithPadding = new Date(now.getTime() + (nonceProperties.getNotBeforePadding() + 1000));
        Date notBefore = token.getNotBefore();
        int comparison = notBefore.compareTo(nowWithPadding);
        // This means that the token is not valid until the given botBefore time.
        // So while unlikely, it's ok to be equal.
        return comparison <= 0;
    }

}
