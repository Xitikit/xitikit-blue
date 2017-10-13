package org.xitikit.blue.noitacitnehtua.api.v2dot0;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.InvalidSignatureException;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.xitikit.blue.noitacitnehtua.api.v2dot0.interfaces.B2CAuthenticationService;
import org.xitikit.blue.noitacitnehtua.api.v2dot0.interfaces.ClaimValidationService;
import org.xitikit.blue.noitacitnehtua.api.v2dot0.interfaces.NonceService;
import org.xitikit.blue.noitacitnehtua.api.v2dot0.interfaces.UrlService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.security.interfaces.RSAPublicKey;

import static org.xitikit.blue.noitacitnehtua.api.v2dot0.VerificationUtil.*;

/**
 * This class implements methods to interface with AzureB2C.
 *
 * @author J. Keith Hoopes
 * @see B2CAuthenticationService
 */
@Slf4j
@Service("simpleB2CAuthenticationService")
public final class SimpleB2CAuthenticationService implements B2CAuthenticationService{

  private final ClaimValidationService claimValidationService;

  private final NonceService nonceService;

  private final UrlService urlService;

  private final RestTemplate restTemplate;

  @Autowired
  public SimpleB2CAuthenticationService(
    final ClaimValidationService claimValidationService,
    final NonceService nonceService,
    final UrlService urlService,
    final RestTemplate restTemplate){

    Assert.notNull(claimValidationService, "Missing required parameter 'claimValidationService' (org.xitikit.blue.noitacitnehtua.api.v2dot0.SimpleB2CAuthenticationService::new)");
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

    final long now = System.currentTimeMillis();
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
      BlueWebToken token = typeSecuredObjectMapper().readValue(
        jwt.getClaims(),
        BlueWebToken.class);
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
        log.warn("Failed to validate notBefore time in token. This could be a replay attack. 'Now' milliseconds: " + now + "; 'NotBefore' milliseconds: " + token
          .getNotBefore()
          .toInstant()
          .toEpochMilli());
        return null;
      }
      if(!claimValidationService.validateExpiration(token, now)){
        log.warn("Failed to validate expiration time in token. This could be a replay attack. 'Now' milliseconds: " + now + "; 'Expiration' milliseconds: " + token
          .getExpiration()
          .toInstant()
          .toEpochMilli());
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
        urlService.wellKnownEndpoint(policy),
        JsonNode.class
      ));
  }
}
