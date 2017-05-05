package org.xitikit.blue.noitacitnehtua;

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
import org.xitikit.blue.gifnoc.sporp.*;
import org.xitikit.blue.nommoc.errors.NotFoundException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static java.net.URLEncoder.*;

/**
 * This class implements methods to interface with AzureB2C.
 *
 * @author J. Keith Hoopes
 * @see B2CAuthenticationService
 */
@Slf4j
public final class SimpleB2CAuthenticationService implements B2CAuthenticationService{

    private static final String STANDARD_CONFIGURATION_QUERY = "&response_mode=form_post&scope=openid&response_type=id_token&prompt=login";
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private static final ObjectMapper mapper = new ObjectMapper();

    private final B2CProperties b2CProperties;
    private final SignUpPolicy signUpPolicy;
    private final SignInPolicy signInPolicy;
    private final SignUpOrSignInPolicy signUpOrSignInPolicy;
    private final ResetPasswordPolicy resetPasswordPolicy;
    private final EditProfilePolicy editProfilePolicy;
    private final SignOutPolicy signOutPolicy;
    private final NonceProperties nonceProperties;
    private final NonceService nonceService;

    public SimpleB2CAuthenticationService(
        B2CProperties b2CProperties,
        SignUpPolicy signUpPolicy,
        SignInPolicy signInPolicy,
        SignUpOrSignInPolicy signUpOrSignInPolicy,
        ResetPasswordPolicy resetPasswordPolicy,
        EditProfilePolicy editProfilePolicy,
        SignOutPolicy signOutPolicy,
        NonceProperties nonceProperties,
        NonceService nonceService){

        this.b2CProperties = b2CProperties;
        this.signUpPolicy = signUpPolicy;
        this.signInPolicy = signInPolicy;
        this.signUpOrSignInPolicy = signUpOrSignInPolicy;
        this.resetPasswordPolicy = resetPasswordPolicy;
        this.editProfilePolicy = editProfilePolicy;
        this.signOutPolicy = signOutPolicy;
        this.nonceProperties = nonceProperties;
        this.nonceService = nonceService;
    }

    @Nonnull
    @Override
    public String getSignUpUrl(){

        return domainUrlPart() +
            policyUrlPart(signUpPolicy.getName()) +
            clientIdUrlPart() +
            nonceUrlPart() +
            redirectUrlPart(signUpPolicy.getRedirectUrl()) +
            STANDARD_CONFIGURATION_QUERY;
    }

    private String domainUrlPart(){

        return "https://login.microsoftonline.com/" + b2CProperties.getDomain();
    }

    private String clientIdUrlPart(){

        return "&client_Id=" + b2CProperties.getAppId();
    }

    private String nonceUrlPart(){

        return (nonceProperties.isDisabled() ? "":"&nonce=" + nonceService.generate());
    }

    private String redirectUrlPart(String redirectUrl){

        assert redirectUrl != null;
        try{
            return "&redirect_uri=" + encode(redirectUrl, "UTF-8");
        }
        catch(UnsupportedEncodingException e){
            throw new NotFoundException(e.getMessage(), e);
        }
    }

    @Nonnull
    @Override
    public String getSignInUrl(){

        return domainUrlPart() +
            policyUrlPart(signInPolicy.getName()) +
            clientIdUrlPart() +
            nonceUrlPart() +
            redirectUrlPart(signInPolicy.getRedirectUrl()) +
            STANDARD_CONFIGURATION_QUERY;
    }

    @Nonnull
    @Override
    public String getSignUpOrSignInUrl(){

        return domainUrlPart() +
            policyUrlPart(signUpOrSignInPolicy.getName()) +
            clientIdUrlPart() +
            nonceUrlPart() +
            redirectUrlPart(signUpOrSignInPolicy.getRedirectUrl()) +
            STANDARD_CONFIGURATION_QUERY;
    }

    @Nonnull
    @Override
    public String getSignOutUrl(){

        if(signOutPolicy.isDisabled()){
            return "";
        }
        try{
            String nameSignOutPolicy = signOutPolicy.getName();
            if(nameSignOutPolicy == null && !signUpOrSignInPolicy.isDisabled()){
                nameSignOutPolicy = signUpOrSignInPolicy.getName();
            }
            //Checks to see if ti is STILL null, and then decides to use the signIngPolicy name instead.
            if(nameSignOutPolicy == null && !signInPolicy.isDisabled()){
                nameSignOutPolicy = signInPolicy.getName();
            }
            if(nameSignOutPolicy == null){
                return "";
            }
            return domainUrlPart() +
                policyUrlPart(nameSignOutPolicy) +
                "&post_logout_redirect_uri=" + encode(signOutPolicy.getRedirectUrl(), "UTF-8") +
                nonceUrlPart();
        }
        catch(UnsupportedEncodingException e){
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    private String policyUrlPart(String policyName){

        assert policyName != null && !"".equals(policyName);
        return "/oauth2/v2.0/authorize?p=" + policyName;
    }

    @Nonnull
    @Override
    public String getProfileUrl(){

        return domainUrlPart() +
            policyUrlPart(editProfilePolicy.getName()) +
            clientIdUrlPart() +
            nonceUrlPart() +
            redirectUrlPart(editProfilePolicy.getRedirectUrl()) +
            STANDARD_CONFIGURATION_QUERY;
    }

    @Nonnull
    @Override
    public String getResetPasswordUrl(){

        return domainUrlPart() +
            policyUrlPart(resetPasswordPolicy.getName()) +
            clientIdUrlPart() +
            nonceUrlPart() +
            redirectUrlPart(resetPasswordPolicy.getRedirectUrl()) +
            STANDARD_CONFIGURATION_QUERY;
    }

    @Nullable
    @Override
    public BlueWebToken decodeAndVerify(@Nonnull String idToken){

        final long now = System.currentTimeMillis();
        if(log.isTraceEnabled()){
            log.trace("Decoding token [" + idToken + "]");
        }
        try{
            Jwt jwt = JwtHelper.decode(idToken);
            String claims = jwt.getClaims();
            BlueWebToken token = mapper.readValue(claims, BlueWebToken.class);
            // Validate the nonce
            if(!nonceService.isValid(token.getNonce())){
                log.warn("Failed to validate nonce in token. This could be a replay attack.");
                return null;
            }
            if(!validateAudience(token)){
                log.warn("Failed to validate audience in token. This could be a replay attack.");
                return null;
            }
            if(!validateIssuer(token)){
                log.warn("Failed to validate issuer of token. This could be a replay attack.");
                return null;
            }
            if(!validateNotBefore(token, now)){
                log.warn("Failed to validate notBefore time in token. This could be a replay attack. 'Now' milliseconds: " + now + "; 'NotBefore' milliseconds: " + token.getNotBefore().toInstant().toEpochMilli());
                return null;
            }
            if(!validateExpiration(token, now)){
                log.warn("Failed to validate expiration time in token. This could be a replay attack. 'Now' milliseconds: " + now + "; 'Expiration' milliseconds: " + token.getExpiration().toInstant().toEpochMilli());
                return null;
            }
            // Get the key ID we need to use to verify the token
            String keyId = getKeyId(idToken);
            if(keyId == null){
                log.warn("Failed to retrieve key ID for token");
                return null;
            }
            // Get the key and verify the JWT signature
            RSAPublicKey key = getKey(keyId, token.getAuthContextReference());
            jwt.verifySignature(new RsaVerifier(key));
            return token;

        }
        catch(IOException | IllegalArgumentException | InvalidSignatureException x){
            log.warn("Failed to extract data from JWT token: " + x.getMessage(), x);
        }
        return null;
    }

    private boolean validateAudience(BlueWebToken token){

        return b2CProperties.getAppId().equals(token.getAudience());
    }

    private boolean validateIssuer(BlueWebToken token){

        final String issuer = token.getIssuer();
        return issuer != null &&
            issuer.startsWith("https://login.microsoftonline.com/") &&
            issuer.endsWith("/v2.0/");
    }

    @SuppressWarnings("unused")
    private boolean validateNotBefore(BlueWebToken token, long now){

        Instant instant = Instant.ofEpochMilli(now + (nonceProperties.getNotBeforePadding() + 1000));
        ZonedDateTime nowWithPadding = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
        ZonedDateTime notBefore = token.getNotBefore();
        int comparison = notBefore.compareTo(nowWithPadding);
        // This means that the token is not valid until the given botBefore time.
        // So while unlikely, it's ok to be equal.
        return comparison <= 0;
    }

    private boolean validateExpiration(BlueWebToken token, long now){

        Instant instant = Instant.ofEpochMilli(now);
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
        ZonedDateTime expiration = token.getExpiration();

        return expiration.compareTo(zonedDateTime) > 0;
    }

    /**
     * Attempts to retrieve the key ID from the JWT token header.
     *
     * @param idToken the JWT token
     *
     * @return the key ID or null
     */
    @Nullable
    private String getKeyId(@Nonnull String idToken){

        try{
            String[] parts = idToken.split("\\.");
            byte[] bytes = Base64.decodeBase64(parts[0]);
            String json = new String(bytes, UTF8);
            JsonNode node = mapper.readValue(json, JsonNode.class);
            if(node.has("kid")){
                return node.get("kid").asText();
            }
        }
        catch(IOException x){
            log.warn("Failed to parse jwt token header: " + x.getMessage(), x);
        }
        return null;
    }

    /**
     * Attempts to get an RSAPublicKey for the given policy.
     *
     * @param keyId  the key ID to fetch
     * @param policy the policy the key belongs to
     *
     * @return the key or null if it could not be fetched
     */
    @Nullable
    private RSAPublicKey getKey(@Nonnull String keyId, @Nonnull String policy){

        RestTemplate restTemplate = new RestTemplate();
        try{
            // Get the meta data
            String url = domainUrlPart() + "/v2.0/.well-known/openid-configuration?p=" + policy;
            JsonNode node = restTemplate.getForObject(url, JsonNode.class);

            // Get keys
            url = node.get("jwks_uri").asText();
            node = restTemplate.getForObject(url, JsonNode.class);
            node = node.get("keys");

            for(JsonNode keyNode : node){
                if(keyNode.get("kid").asText().equals(keyId)){
                    return parseKey(keyNode.get("n").asText(), keyNode.get("e").asText());
                }
            }

        }
        catch(RestClientException | NullPointerException x){
            log.error("Error retrieving RSA keys for policy [" + policy + "]: " + x.getMessage(), x);
        }
        return null;
    }

    /**
     * Accepts a modulus and exponent base64 encoded string and produces an RSAPublicKey.
     *
     * @param n the base64 encoded modulus
     * @param e the base64 encoded exponent
     *
     * @return the key or null if it could not be created
     */
    @Nullable
    private RSAPublicKey parseKey(@Nonnull String n, @Nonnull String e){

        try{
            byte[] modulus = Base64.decodeBase64(n);
            byte[] exponent = Base64.decodeBase64(e);
            RSAPublicKeySpec spec = new RSAPublicKeySpec(
                new BigInteger(1, modulus),
                new BigInteger(1, exponent));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (RSAPublicKey) keyFactory.generatePublic(spec);
        }
        catch(NoSuchAlgorithmException | InvalidKeySpecException x){
            log.error("Failed to parse RSA public key: " + x.getMessage(), x);
        }
        return null;
    }
}
