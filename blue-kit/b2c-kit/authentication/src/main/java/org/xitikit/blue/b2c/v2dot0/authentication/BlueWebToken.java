package org.xitikit.blue.b2c.v2dot0.authentication;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.annotation.Nullable;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hold claim data parsed from an Azure token.
 *
 * Example web token claim:
 * {
 * "exp": 1234567890,
 * "nbf": 1234567890,
 * "ver": "1.0",
 * "iss": "https://login.microsoftonline.com/11111111-1111-1111-1111-111111111111/v2.0/",
 * "acr": "b2c_1_what-you-named-the-policy",
 * "sub": "Not supported currently. Use oid claim.",
 * "aud": "11111111-1111-1111-1111-111111111111",
 * "nonce": "11111111-1111-1111-1111-111111111111",
 * "iat": 1234567890,
 * "auth_time": 1234567890,
 * "oid": "11111111-1111-1111-1111-111111111111",
 * "given_name": "One Punch",
 * "family_name": "Saitama",
 * "emails": [
 * "caped.baldy@heroassociation.org"
 * ]
 * }
 *
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlueWebToken{

    private final Map<String, Object> additionalProperties = new HashMap<>();

    /**
     * This is the version of the ID token, as defined by Azure AD.
     */
    @Nullable
    @JsonProperty("ver")
    private String version;

    /**
     * This claim identifies the security token service (STS) that constructs and returns the token. It also
     * identifies the Azure AD directory in which the user was authenticated. Your app should validate the
     * issuer claim to ensure that the token came from the v2.0 endpoint.
     */
    @Nullable
    @JsonProperty("iss")
    private String issuer;

    /**
     * This is the name of the policy that was used to acquire the ID token if acr is set.
     */
    @Nullable
    @JsonProperty("acr")
    private String authContextReference;

    /**
     * This is the name of the policy that was used to acquire the ID token if acr is set.
     */
    @Nullable
    @JsonProperty("tfp")
    private String trustedFrameworkPolicy;

    /**
     * This is a principal about which the token asserts information, such as the user of an app. This value
     * is immutable and cannot be reassigned or reused. It can be used to perform authorization checks safely,
     * such as when the token is used to access a resource. However, the subject claim is not yet implemented
     * in the Azure AD B2CProperties preview. You should configure your policies to include the object ID oid claim and
     * use its value to identify users, rather than use the subject claim for authorization.
     */
    @Nullable
    @JsonProperty("sub")
    private String subject;

    /**
     * An audience claim identifies the intended recipient of the token. For ID tokens, the audience is your
     * app's Application ID, as assigned to your app in the app registration portal. Your app should validate
     * this value and reject the token if it does not match.
     */
    @Nullable
    @JsonProperty("aud")
    private String audience;

    /**
     * A nonce is a strategy used to mitigate token replay attacks. Your app can specify a nonce in an
     * authorization request by using the nonce query parameter. The value you provide in the request will be
     * emitted unmodified in the nonce claim of the ID token. This allows your app to verify the value against
     * the value it specified on the request, which associates the app's session with a given ID token. Your
     * app should perform this validation during the ID token validation process.
     */
    @Nullable
    @JsonProperty("nonce")
    private String nonce;

    /**
     * The object ID.
     */
    @Nullable
    @JsonProperty("oid")
    private String objectId;

    /**
     * A code hash is included in an ID token only when the token is issued together with an OAuth
     * 2.0 authorization code. A code hash can be used to validate the authenticity of an authorization
     * code. See the OpenID Connect specification for more details on how to perform this validation.
     */
    @Nullable
    @JsonProperty("c_hash")
    private String codeHash;

    /**
     * An access token hash is included in an ID token only when the token is issued together with an
     * OAuth 2.0 access token. An access token hash can be used to validate the authenticity of an access
     * token. See the OpenID Connect specification for more details on how to perform this validation.
     */
    @Nullable
    @JsonProperty("at_hash")
    private String accessTokenHash;

    /**
     * The identity provider used to authenticate the user.
     */
    @Nullable
    @JsonProperty("idp")
    private String identityProvider;

    /**
     * The user's first name.
     */
    @Nullable
    @JsonProperty("given_name")
    private String givenName;

    /**
     * The user's surname, or last name.
     */
    @Nullable
    @JsonProperty("family_name")
    private String familyName;

    /**
     * The expiration time claim is the time at which the token becomes invalid, represented in epoch time.
     * Your app should use this claim to verify the validity of the token lifetime.
     */
    @Nullable
    @JsonProperty("exp")
    @JsonDeserialize(using = UtcUnixDateDeserializer.class)
    @JsonSerialize(using = UtcUnixDateSerializer.class)
    private ZonedDateTime expiration;

    /**
     * This claim is the time at which the token becomes valid, represented in epoch time. This is usually
     * the same as the time the token was issued. Your app should use this claim to verify the validity of
     * the token lifetime.
     */
    @Nullable
    @JsonProperty("nbf")
    @JsonDeserialize(using = UtcUnixDateDeserializer.class)
    @JsonSerialize(using = UtcUnixDateSerializer.class)
    private ZonedDateTime notBefore;

    /**
     * This claim is the time at which the token was issued, represented in epoch time.
     */
    @Nullable
    @JsonProperty("iat")
    @JsonDeserialize(using = UtcUnixDateDeserializer.class)
    @JsonSerialize(using = UtcUnixDateSerializer.class)
    private ZonedDateTime issuedAt;

    /**
     * This claim is the time at which a user last entered credentials, represented in epoch time.
     */
    @Nullable
    @JsonProperty("auth_time")
    @JsonDeserialize(using = UtcUnixDateDeserializer.class)
    @JsonSerialize(using = UtcUnixDateSerializer.class)
    private ZonedDateTime authTime;

    /**
     * The user's email addresses.
     */
    private List<String> emails;

    @JsonProperty("newUser")
    private boolean newUser;

    /**
     * Returns the first email in the list of emails for the user if there is one.
     *
     * @return the first email listed or null
     */
    @Nullable
    @JsonIgnore
    public String getFirstEmail(){

        return emails != null && !emails.isEmpty() ? emails.get(0) : null;
    }

    @Nullable
    public ZonedDateTime getExpiration(){

        return expiration;
    }

    public void setExpiration(@Nullable final ZonedDateTime expiration){

        this.expiration = expiration;
    }

    @Nullable
    public ZonedDateTime getNotBefore(){

        return notBefore;
    }

    public void setNotBefore(@Nullable final ZonedDateTime notBefore){

        this.notBefore = notBefore;
    }

    @Nullable
    public String getVersion(){

        return version;
    }

    public void setVersion(@Nullable final String version){

        this.version = version;
    }

    @Nullable
    public String getIssuer(){

        return issuer;
    }

    public void setIssuer(@Nullable final String issuer){

        this.issuer = issuer;
    }

    @Nullable
    public String getAuthContextReference(){

        return authContextReference;
    }

    public void setAuthContextReference(@Nullable final String authContextReference){

        this.authContextReference = authContextReference;
    }

    @Nullable
    public String getSubject(){

        return subject;
    }

    public void setSubject(@Nullable final String subject){

        this.subject = subject;
    }

    @Nullable
    public String getAudience(){

        return audience;
    }

    public void setAudience(@Nullable final String audience){

        this.audience = audience;
    }

    @Nullable
    public String getNonce(){

        return nonce;
    }

    public void setNonce(@Nullable final String nonce){

        this.nonce = nonce;
    }

    @Nullable
    public ZonedDateTime getIssuedAt(){

        return issuedAt;
    }

    public void setIssuedAt(@Nullable final ZonedDateTime issuedAt){

        this.issuedAt = issuedAt;
    }

    @Nullable
    public ZonedDateTime getAuthTime(){

        return authTime;
    }

    public void setAuthTime(@Nullable final ZonedDateTime authTime){

        this.authTime = authTime;
    }

    @Nullable
    public String getObjectId(){

        return objectId;
    }

    public void setObjectId(@Nullable final String objectId){

        this.objectId = objectId;
    }

    @Nullable
    public String getCodeHash(){

        return codeHash;
    }

    public void setCodeHash(@Nullable final String codeHash){

        this.codeHash = codeHash;
    }

    @Nullable
    public String getAccessTokenHash(){

        return accessTokenHash;
    }

    public void setAccessTokenHash(@Nullable final String accessTokenHash){

        this.accessTokenHash = accessTokenHash;
    }

    @Nullable
    public String getIdentityProvider(){

        return identityProvider;
    }

    public void setIdentityProvider(@Nullable final String identityProvider){

        this.identityProvider = identityProvider;
    }

    @Nullable
    public String getGivenName(){

        return givenName;
    }

    public void setGivenName(@Nullable final String givenName){

        this.givenName = givenName;
    }

    @Nullable
    public String getFamilyName(){

        return familyName;
    }

    public void setFamilyName(@Nullable final String familyName){

        this.familyName = familyName;
    }

    @Nullable
    public List<String> getEmails(){

        return emails;
    }

    public void setEmails(@Nullable final List<String> emails){

        this.emails = emails;
    }

    public boolean isNewUser(){

        return newUser;
    }

    public void setNewUser(final boolean newUser){

        this.newUser = newUser;
    }

    @Nullable
    public String getTrustedFrameworkPolicy(){

        return trustedFrameworkPolicy;
    }

    public void setTrustedFrameworkPolicy(@Nullable final String trustedFrameworkPolicy){

        this.trustedFrameworkPolicy = trustedFrameworkPolicy;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties(){

        return additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(final String key, final Object value){

        additionalProperties.put(key, value);
    }
}
