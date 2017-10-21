package org.xitikit.blue.kit.authentication.v2dot0;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.annotation.Nullable;
import java.time.ZonedDateTime;
import java.util.List;

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

    /**
     * The expiration time claim is the time at which the token becomes invalid, represented in epoch time.
     * Your app should use this claim to verify the validity of the token lifetime.
     */
    @JsonProperty("exp")
    @JsonDeserialize(using = UtcUnixDateDeserializer.class)
    @JsonSerialize(using = UtcUnixDateSerializer.class)
    private ZonedDateTime expiration;

    /**
     * This claim is the time at which the token becomes valid, represented in epoch time. This is usually
     * the same as the time the token was issued. Your app should use this claim to verify the validity of
     * the token lifetime.
     */
    @JsonProperty("nbf")
    @JsonDeserialize(using = UtcUnixDateDeserializer.class)
    @JsonSerialize(using = UtcUnixDateSerializer.class)
    private ZonedDateTime notBefore;

    /**
     * This is the version of the ID token, as defined by Azure AD.
     */
    @JsonProperty("ver")
    private String version;

    /**
     * This claim identifies the security token service (STS) that constructs and returns the token. It also
     * identifies the Azure AD directory in which the user was authenticated. Your app should validate the
     * issuer claim to ensure that the token came from the v2.0 endpoint.
     */
    @JsonProperty("iss")
    private String issuer;

    /**
     * This is the name of the policy that was used to acquire the ID token.
     */
    @JsonProperty("acr")
    private String authContextReference;

    /**
     * This is a principal about which the token asserts information, such as the user of an app. This value
     * is immutable and cannot be reassigned or reused. It can be used to perform authorization checks safely,
     * such as when the token is used to access a resource. However, the subject claim is not yet implemented
     * in the Azure AD B2CProperties preview. You should configure your policies to include the object ID oid claim and
     * use its value to identify users, rather than use the subject claim for authorization.
     */
    @JsonProperty("sub")
    private String subject;

    /**
     * An audience claim identifies the intended recipient of the token. For ID tokens, the audience is your
     * app's Application ID, as assigned to your app in the app registration portal. Your app should validate
     * this value and reject the token if it does not match.
     */
    @JsonProperty("aud")
    private String audience;

    /**
     * A nonce is a strategy used to mitigate token replay attacks. Your app can specify a nonce in an
     * authorization request by using the nonce query parameter. The value you provide in the request will be
     * emitted unmodified in the nonce claim of the ID token. This allows your app to verify the value against
     * the value it specified on the request, which associates the app's session with a given ID token. Your
     * app should perform this validation during the ID token validation process.
     */
    @JsonProperty("nonce")
    private String nonce;

    /**
     * This claim is the time at which the token was issued, represented in epoch time.
     */
    @JsonProperty("iat")
    @JsonDeserialize(using = UtcUnixDateDeserializer.class)
    @JsonSerialize(using = UtcUnixDateSerializer.class)
    private ZonedDateTime issuedAt;

    /**
     * This claim is the time at which a user last entered credentials, represented in epoch time.
     */
    @JsonProperty("auth_time")
    @JsonDeserialize(using = UtcUnixDateDeserializer.class)
    @JsonSerialize(using = UtcUnixDateSerializer.class)
    private ZonedDateTime authTime;

    /**
     * The object ID.
     */
    @JsonProperty("oid")
    private String objectId;

    /**
     * A code hash is included in an ID token only when the token is issued together with an OAuth
     * 2.0 authorization code. A code hash can be used to validate the authenticity of an authorization
     * code. See the OpenID Connect specification for more details on how to perform this validation.
     */
    @JsonProperty("c_hash")
    private String codeHash;

    /**
     * An access token hash is included in an ID token only when the token is issued together with an
     * OAuth 2.0 access token. An access token hash can be used to validate the authenticity of an access
     * token. See the OpenID Connect specification for more details on how to perform this validation.
     */
    @JsonProperty("at_hash")
    private String accessTokenHash;

    /**
     * The identity provider used to authenticate the user.
     */
    @JsonProperty("idp")
    private String identityProvider;

    /**
     * The user's first name.
     */
    @JsonProperty("given_name")
    private String firstName;

    /**
     * The user's last name.
     */
    @JsonProperty("family_name")
    private String lastName;

    /**
     * The user's email addresses.
     */
    @Nullable
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

    public ZonedDateTime getExpiration(){

        return expiration;
    }

    public void setExpiration(final ZonedDateTime expiration){

        this.expiration = expiration;
    }

    public ZonedDateTime getNotBefore(){

        return notBefore;
    }

    public void setNotBefore(final ZonedDateTime notBefore){

        this.notBefore = notBefore;
    }

    public String getVersion(){

        return version;
    }

    public void setVersion(final String version){

        this.version = version;
    }

    public String getIssuer(){

        return issuer;
    }

    public void setIssuer(final String issuer){

        this.issuer = issuer;
    }

    public String getAuthContextReference(){

        return authContextReference;
    }

    public void setAuthContextReference(final String authContextReference){

        this.authContextReference = authContextReference;
    }

    public String getSubject(){

        return subject;
    }

    public void setSubject(final String subject){

        this.subject = subject;
    }

    public String getAudience(){

        return audience;
    }

    public void setAudience(final String audience){

        this.audience = audience;
    }

    public String getNonce(){

        return nonce;
    }

    public void setNonce(final String nonce){

        this.nonce = nonce;
    }

    public ZonedDateTime getIssuedAt(){

        return issuedAt;
    }

    public void setIssuedAt(final ZonedDateTime issuedAt){

        this.issuedAt = issuedAt;
    }

    public ZonedDateTime getAuthTime(){

        return authTime;
    }

    public void setAuthTime(final ZonedDateTime authTime){

        this.authTime = authTime;
    }

    public String getObjectId(){

        return objectId;
    }

    public void setObjectId(final String objectId){

        this.objectId = objectId;
    }

    public String getCodeHash(){

        return codeHash;
    }

    public void setCodeHash(final String codeHash){

        this.codeHash = codeHash;
    }

    public String getAccessTokenHash(){

        return accessTokenHash;
    }

    public void setAccessTokenHash(final String accessTokenHash){

        this.accessTokenHash = accessTokenHash;
    }

    public String getIdentityProvider(){

        return identityProvider;
    }

    public void setIdentityProvider(final String identityProvider){

        this.identityProvider = identityProvider;
    }

    public String getFirstName(){

        return firstName;
    }

    public void setFirstName(final String firstName){

        this.firstName = firstName;
    }

    public String getLastName(){

        return lastName;
    }

    public void setLastName(final String lastName){

        this.lastName = lastName;
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
}
