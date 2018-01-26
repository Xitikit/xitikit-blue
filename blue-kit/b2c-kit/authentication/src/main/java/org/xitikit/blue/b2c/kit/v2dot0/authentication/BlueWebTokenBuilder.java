package org.xitikit.blue.b2c.kit.v2dot0.authentication;

import javax.annotation.Nullable;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Helper for creating BlueWebToken manually. *
 * This is not thread-safe.
 *
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
public final class BlueWebTokenBuilder{

  private final Map<String, Object> store;

  private BlueWebTokenBuilder(){

    store = new ConcurrentHashMap<>();
  }

  public static BlueWebTokenBuilder instance(){

    return new BlueWebTokenBuilder();
  }

  public BlueWebTokenBuilder clear(){

    store.clear();
    return this;
  }

  @SuppressWarnings("unchecked")
  public BlueWebToken build(){

    BlueWebToken token = new BlueWebToken();
    token.setAccessTokenHash((String) store.get("accessTokenHash"));
    token.setAudience((String) store.get("audience"));
    token.setAuthContextReference((String) store.get("authContextReference"));
    token.setAuthTime((ZonedDateTime) store.get("authTime"));
    token.setCodeHash((String) store.get("codeHash"));
    token.setEmails((List<String>) store.get("emails"));
    token.setExpiration((ZonedDateTime) store.get("expiration"));
    token.setIdentityProvider((String) store.get("identityProvider"));
    token.setIssuedAt((ZonedDateTime) store.get("issuedAt"));
    token.setIssuer((String) store.get("issuer"));
    token.setNewUser((Boolean) store.get("newUser"));
    token.setNonce((String) store.get("nonce"));
    token.setNotBefore((ZonedDateTime) store.get("notBefore"));
    token.setObjectId((String) store.get("objectId"));
    token.setSubject((String) store.get("subject"));
    token.setTrustedFrameworkPolicy((String) store.get("trustedFrameworkPolicy"));
    token.setVersion((String) store.get("version"));
    Map<String, Object> props = (Map<String, Object>) store.get("additionalProperties");
    if(props != null && props.size() > 0){
      props
        .entrySet()
        .stream()
        .filter(e -> e != null && e.getKey() != null)
        .forEach(
          entry -> token.set(entry.getKey(), entry.getValue())
        );
    }
    return token;
  }

  public BlueWebTokenBuilder with(
    final String accessTokenHash,
    final String audience,
    final ZonedDateTime authTime,
    final String authContextReference,
    final String codeHash,
    final List<String> emails,
    final ZonedDateTime expiration,
    final String identityProvider,
    final ZonedDateTime issuedAt,
    final String issuer,
    final boolean newUser,
    final String nonce,
    final ZonedDateTime notBefore,
    final String objectId,
    final String subject,
    final String trustedFrameworkPolicy,
    final String version,
    final Map<String, String> additionalProperties
  ){

    store.put("accessTokenHash", accessTokenHash);
    store.put("audience", audience);
    store.put("authContextReference", authContextReference);
    store.put("authTime", authTime);
    store.put("codeHash", codeHash);
    store.put("emails", emails);
    store.put("expiration", expiration);
    store.put("identityProvider", identityProvider);
    store.put("issuedAt", issuedAt);
    store.put("issuer", issuer);
    store.put("newUser", newUser);
    store.put("nonce", nonce);
    store.put("notBefore", notBefore);
    store.put("objectId", objectId);
    store.put("subject", subject);
    store.put("trustedFrameworkPolicy", trustedFrameworkPolicy);
    store.put("version", version);
    store.put("additionalProperties", additionalProperties);

    return this;
  }

  public BlueWebTokenBuilder withExpiration(@Nullable final ZonedDateTime expiration){

    store.put("expiration", expiration);
    return this;
  }

  public BlueWebTokenBuilder withNotBefore(@Nullable final ZonedDateTime notBefore){

    store.put("notBefore", notBefore);
    return this;
  }

  public BlueWebTokenBuilder withVersion(@Nullable final String version){

    store.put("version", version);
    return this;
  }

  public BlueWebTokenBuilder withIssuer(@Nullable final String issuer){

    store.put("issuer", issuer);
    return this;
  }

  public BlueWebTokenBuilder withAuthContextReference(@Nullable final String authContextReference){

    store.put("authContextReference", authContextReference);
    return this;
  }

  public BlueWebTokenBuilder withSubject(@Nullable final String subject){

    store.put("subject", subject);
    return this;
  }

  public BlueWebTokenBuilder withAudience(@Nullable final String audience){

    store.put("audience", audience);
    return this;
  }

  public BlueWebTokenBuilder withNonce(@Nullable final String nonce){

    store.put("nonce", nonce);
    return this;
  }

  public BlueWebTokenBuilder withIssuedAt(@Nullable final ZonedDateTime issuedAt){

    store.put("issuedAt", issuedAt);
    return this;
  }

  public BlueWebTokenBuilder withAuthTime(@Nullable final ZonedDateTime authTime){

    store.put("authTime", authTime);
    return this;
  }

  public BlueWebTokenBuilder withObjectId(@Nullable final String objectId){

    store.put("objectId", objectId);
    return this;
  }

  public BlueWebTokenBuilder withCodeHash(@Nullable final String codeHash){

    store.put("codeHash", codeHash);
    return this;
  }

  public BlueWebTokenBuilder withAccessTokenHash(@Nullable final String accessTokenHash){

    store.put("accessTokenHash", accessTokenHash);
    return this;
  }

  public BlueWebTokenBuilder withIdentityProvider(@Nullable final String identityProvider){

    store.put("identityProvider", identityProvider);
    return this;
  }

  public BlueWebTokenBuilder withEmails(@Nullable final List<String> emails){

    store.put("emails", emails);
    return this;
  }

  public BlueWebTokenBuilder withNewUser(final boolean newUser){

    store.put("newUser", newUser);
    return this;
  }

  public BlueWebTokenBuilder withTrustedFrameworkPolicy(@Nullable final String trustedFrameworkPolicy){

    store.put("trustedFrameworkPolicy", trustedFrameworkPolicy);
    return this;
  }

  public BlueWebTokenBuilder withAdditionalProperties(Map<String, Object> additionalProperties){

    store.put("additionalProperties", additionalProperties);
    return this;
  }

  @SuppressWarnings("unchecked")
  public BlueWebTokenBuilder withProperty(final String key, final Object value){

    Map<String, Object> additionalProperties = (Map<String, Object>) store.get("additionalProperties");
    if(additionalProperties == null){
      additionalProperties = new ConcurrentHashMap<>();
      store.put("additionalProperties", additionalProperties);
    }
    additionalProperties.put(key, value);
    return this;
  }
}
