package org.xitikit.blue.noitacitnehtua;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.NullNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.InvalidSignatureException;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.xitikit.blue.gifnoc.sporp.B2CProperties;
import org.xitikit.blue.gifnoc.sporp.NonceProperties;

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
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * This class implements methods to interface with AzureB2C.
 *
 * @author J. Keith Hoopes
 * @see B2CAuthenticationService
 */
@Slf4j
@Service("simpleB2CAuthenticationService")
public final class SimpleB2CAuthenticationService implements B2CAuthenticationService{

  private static final Charset UTF8 = Charset.forName("UTF-8");

  private static final ObjectMapper mapper = new ObjectMapper();

  private final B2CProperties b2CProperties;

  private final NonceProperties nonceProperties;

  private final NonceService nonceService;

  private final BlueUrlService blueUrlService;

  private final RestTemplate restTemplate;

  public SimpleB2CAuthenticationService(
    final B2CProperties b2CProperties,
    final NonceProperties nonceProperties,
    final NonceService nonceService,
    final BlueUrlService blueUrlService,
    final RestTemplate restTemplate){

    this.b2CProperties = b2CProperties;
    this.nonceProperties = nonceProperties;
    this.nonceService = nonceService;
    this.blueUrlService = blueUrlService;
    this.restTemplate = restTemplate;
  }

  @Nullable
  @Override
  public BlueWebToken decodeAndVerify(@Nonnull final String idToken){

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
        log.warn("Failed to validate notBefore time in token. This could be a replay attack. 'Now' milliseconds: " + now + "; 'NotBefore' milliseconds: " + token
          .getNotBefore()
          .toInstant()
          .toEpochMilli());
        return null;
      }
      if(!validateExpiration(token, now)){
        log.warn("Failed to validate expiration time in token. This could be a replay attack. 'Now' milliseconds: " + now + "; 'Expiration' milliseconds: " + token
          .getExpiration()
          .toInstant()
          .toEpochMilli());
        return null;
      }
      // Get the key ID we need to use to verify the token
      String keyId = getKeyId(idToken);
      if("".equals(keyId.trim())){
        log.warn("Failed to retrieve key ID for token");
        return null;
      }
      // Get the key and verify the JWT signature
      RSAPublicKey key = rsaPublicKey(keyId, token.getAuthContextReference());
      jwt.verifySignature(new RsaVerifier(key));
      return token;

    }catch(IOException | IllegalArgumentException | InvalidSignatureException x){
      log.warn("Failed to extract data from JWT token: " + x.getMessage(), x);
    }
    return null;
  }

  private boolean validateAudience(final BlueWebToken token){

    return b2CProperties
      .getAppId()
      .equals(token.getAudience());
  }

  @SuppressWarnings("unused")
  private boolean validateNotBefore(final BlueWebToken token, final long now){

    Instant instant = Instant.ofEpochMilli(now + (nonceProperties.getNotBeforePadding() + 1000));
    ZonedDateTime nowWithPadding = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
    ZonedDateTime notBefore = token.getNotBefore();
    int comparison = notBefore.compareTo(nowWithPadding);
    // This means that the token is not valid until the given botBefore time.
    // So while unlikely, it's ok to be equal.
    return comparison <= 0;
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
    @Nonnull final String keyId,
    @Nonnull final String policy){

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
        blueUrlService.wellKnownEndpoint(policy),
        JsonNode.class
      ));
  }

  private static boolean validateIssuer(final BlueWebToken token){

    final String issuer = token.getIssuer();
    return issuer != null && issuer.startsWith("https://login.microsoftonline.com/") && issuer.endsWith("/v2.0/");
  }

  private static boolean validateExpiration(final BlueWebToken token, final long now){

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
  @Nonnull
  private static String getKeyId(@Nonnull final String idToken){

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
  private static String getKeyId(@Nonnull final JsonNode node){

    return node.has("kid") ? node.get("kid").asText() : "";
  }

  /**
   * Parses out the modulus.
   *
   * @param kidNode JsonNode
   *
   * @return the modulus from the given node if present.
   */
  @Nonnull
  private static byte[] modulus(@Nonnull final JsonNode kidNode){

    return safeDecodeBase64(encodedModulus(kidNode));
  }

  /**
   * Parses out the exponent.
   *
   * @param kidNode JsonNode
   *
   * @return the exponent from the given node if present.
   */
  @Nonnull
  private static byte[] exponent(@Nonnull final JsonNode kidNode){

    return safeDecodeBase64(encodedExponent(kidNode));
  }

  @Nonnull
  private static String encodedModulus(@Nonnull final JsonNode keyNode){

    return keyNode.has("n") ? keyNode.get("n").asText() : "";
  }

  @Nonnull
  private static String encodedExponent(@Nonnull final JsonNode keyNode){

    return keyNode.has("e") ? keyNode.get("e").asText() : "";
  }

  @Nonnull
  private static byte[] safeDecodeBase64(@Nullable final String base){

    return base == null || "".equals(base.trim()) ?
      new byte[0] :
      Base64.decodeBase64(base);
  }

  @Nonnull
  private static JsonNode kidForKeyId(
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
  private static JsonNode safeNode(@Nullable final JsonNode node){

    return node == null ? NullNode.getInstance() : node;
  }

  @Nonnull
  private static String jwksUri(@Nonnull final JsonNode node){

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
  private static RSAPublicKey parseKey(
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
  private static RSAPublicKeySpec rsaPublicKeySpec(
    @Nonnull final byte[] modulus,
    @Nonnull final byte[] exponent){

    return new RSAPublicKeySpec(
      new BigInteger(1, modulus),
      new BigInteger(1, exponent));
  }
}
