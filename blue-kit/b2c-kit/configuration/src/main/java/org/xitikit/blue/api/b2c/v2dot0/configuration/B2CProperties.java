package org.xitikit.blue.api.b2c.v2dot0.configuration;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
public class B2CProperties{

    /**
     * This is the application id, and not the secret
     */
    private String appId;

    /**
     * This is the secret created when you clicked "Generate Key" during the application registration
     */
    private String appKey;

    /**
     * The domain associated with the Azure AD B2CProperties directory. Example: xitikitblue.onmicrosoft.com
     */
    private String domain;

    /**
     * This is the name of the "reset password" policy specific to the environment the application is deployed on.
     */
    private String resetPasswordPolicy;

    /**
     * The endpoint that a user is redirected to after completing an azure "reset password" policy flow.
     */
    private String resetPasswordRedirect;

    /**
     * In milliseconds. How long the user has to complete an action inside of azure.
     */
    private String nonceTimeout;

    /**
     * The "not before" token sometimes comes back from microsoft in the future. In milliseconds, this lets us pad the "now"
     * time, in effect saying "as long as it's not TOO far in the future, we're okay with it."
     */
    private String notBeforePaddingMilliseconds;

    public String getAppId(){

        return appId;
    }

    public void setAppId(final String appId){

        this.appId = appId;
    }

    public String getAppKey(){

        return appKey;
    }

    public void setAppKey(final String appKey){

        this.appKey = appKey;
    }

    public String getDomain(){

        return domain;
    }

    public void setDomain(final String domain){

        this.domain = domain;
    }

    public String getResetPasswordPolicy(){

        return resetPasswordPolicy;
    }

    public void setResetPasswordPolicy(final String resetPasswordPolicy){

        this.resetPasswordPolicy = resetPasswordPolicy;
    }

    public String getResetPasswordRedirect(){

        return resetPasswordRedirect;
    }

    public void setResetPasswordRedirect(final String resetPasswordRedirect){

        this.resetPasswordRedirect = resetPasswordRedirect;
    }

    public String getNonceTimeout(){

        return nonceTimeout;
    }

    public void setNonceTimeout(final String nonceTimeout){

        this.nonceTimeout = nonceTimeout;
    }

    public String getNotBeforePaddingMilliseconds(){

        return notBeforePaddingMilliseconds;
    }

    public void setNotBeforePaddingMilliseconds(final String notBeforePaddingMilliseconds){

        this.notBeforePaddingMilliseconds = notBeforePaddingMilliseconds;
    }
}
