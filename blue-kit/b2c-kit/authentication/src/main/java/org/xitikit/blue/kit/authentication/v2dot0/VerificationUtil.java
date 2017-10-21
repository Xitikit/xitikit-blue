package org.xitikit.blue.kit.authentication.v2dot0;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.NullNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.util.Assert;

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

import static org.xitikit.blue.kit.authentication.v2dot0.TimeComparison.*;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
@Slf4j
public final class VerificationUtil{

    private static final Charset UTF8 = Charset.forName("UTF-8");

    /**
     * A type secure object mapper.
     *
     * Refer to 'cve-2017-4995: https://pivotal.io/security/cve-2017-4995'
     * for more information.
     */
    private static final ObjectMapper mapper = typeSecuredObjectMapper();

    /**
     * Creates a type secure object mapper.
     *
     * This builder prevents desirialization vulnerabilities caused
     * by malicious and crafted JSON. This method allows applications
     * using this api to not enable spring security.
     *
     * Refer to 'cve-2017-4995: https://pivotal.io/security/cve-2017-4995'
     * for more information.
     */
    public static ObjectMapper typeSecuredObjectMapper(){

        ObjectMapper mapper = new ObjectMapper();
        SecurityJackson2Modules.enableDefaultTyping(mapper);
        return mapper;
    }

    /**
     * Verifies that the issuer of the token is the microsoft
     * Azure AD B2C authentication api.
     *
     * @param token the parsed id_token being verified
     *
     * @return true if the issuer is the Azure AD B2C
     *   authentication api.
     */
    public static boolean validateIssuer(final BlueWebToken token){

        Assert.notNull(token, "Missing required parameter 'token' (VerificationUtil::validateIssuer)");

        final String issuer = token.getIssuer();

        return issuer != null &&
          issuer.startsWith("https://login.microsoftonline.com/") &&
          issuer.endsWith("/v2.0/");
    }

    /**
     * Determine whether the token is now expired.
     *
     * @param token the parsed id_token received from Azure AD B2C
     * @param now The epoch time in milliseconds
     *
     * @return true if the token is still valid, false if it has expired.
     */
    public static boolean validateExpiration(final BlueWebToken token, @Nullable final Long now){

        Assert.notNull(token, "Missing required parameter 'token' (VerificationUtil::validateExpiration)");

        return comparisonOf(
          token.getExpiration(),
          now
        ).isGreater();
    }

    /**
     * Attempts to retrieve the key ID from the JWT token header.
     *
     * @param idToken the JWT token
     *
     * @return the key ID or null
     */
    @Nonnull
    public static String getKeyId(@Nonnull final String idToken){

        try{
            return getKeyId(
              mapper.readValue(
                new String(
                  safeDecodeBase64(
                    idToken.split("\\.")[0]
                  ),
                  UTF8
                ),
                JsonNode.class));

        }catch(IOException x){
            log.warn("Failed to parse jwt token header: " + x.getMessage(), x);
        }
        return "";
    }

    @Nonnull
    public static String getKeyId(@Nonnull final JsonNode node){

        return node.has("kid") ? node.get("kid").asText() : "";
    }

    @Nonnull
    public static byte[] safeDecodeBase64(@Nullable final String base){

        return base == null || "".equals(base.trim()) ?
          new byte[0] :
          Base64.decodeBase64(base);
    }

    /**
     * Parses out the modulus.
     *
     * @param kidNode JsonNode
     *
     * @return the modulus from the given node if present.
     */
    @Nonnull
    public static byte[] modulus(@Nonnull final JsonNode kidNode){

        return safeDecodeBase64(encodedModulus(kidNode));
    }

    @Nonnull
    public static String encodedModulus(@Nonnull final JsonNode keyNode){

        return keyNode.has("n") ? keyNode.get("n").asText() : "";
    }

    /**
     * Parses out the exponent.
     *
     * @param kidNode JsonNode
     *
     * @return the exponent from the given node if present.
     */
    @Nonnull
    public static byte[] exponent(@Nonnull final JsonNode kidNode){

        return safeDecodeBase64(encodedExponent(kidNode));
    }

    @Nonnull
    public static String encodedExponent(@Nonnull final JsonNode keyNode){

        return keyNode.has("e") ? keyNode.get("e").asText() : "";
    }

    @Nonnull
    public static JsonNode kidForKeyId(
      @Nonnull final JsonNode node,
      @Nonnull final String keyId){

        for(JsonNode keyNode : node){
            if(keyNode
              .get("kid")
              .asText()
              .equals(keyId)){

                return keyNode;
            }
        }
        return NullNode.getInstance();
    }

    @Nonnull
    public static JsonNode safeNode(@Nullable final JsonNode node){

        return node == null ? NullNode.getInstance() : node;
    }

    @Nonnull
    public static String jwksUri(@Nonnull final JsonNode node){

        return node.has("jwks_uri") ? node.get("jwks_uri").asText() : "";
    }

    /**
     * Accepts a modulus and exponent base64 encoded string and produces an RSAPublicKey.
     *
     * @param modulus the decoded modulus
     * @param exponent the decoded exponent
     *
     * @return the key or null if it could not be created
     */
    @Nullable
    public static RSAPublicKey parseKey(
      @Nonnull final byte[] modulus,
      @Nonnull final byte[] exponent){

        try{

            return (RSAPublicKey) KeyFactory
              .getInstance("RSA")
              .generatePublic(
                rsaPublicKeySpec(modulus, exponent)
              );

        }catch(NoSuchAlgorithmException | InvalidKeySpecException x){
            log.error("Failed to parse RSA public key: " + x.getMessage(), x);
        }
        return null;
    }

    @Nonnull
    public static RSAPublicKeySpec rsaPublicKeySpec(
      @Nonnull final byte[] modulus,
      @Nonnull final byte[] exponent){

        return new RSAPublicKeySpec(
          new BigInteger(1, modulus),
          new BigInteger(1, exponent));
    }
}
