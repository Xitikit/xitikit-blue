package org.xitikit.blue.common.properties;

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
     * The "not before" token sometimes comes back from microsoft in the future. In milliseconds, this lets us pad the "now"
     * time, in effect saying "as long as it's not TOO far in the future, we're okay with it."
     */
    private String notBeforePaddingMilliseconds;

    /**
     * Configuration options for using a nonce for validation.
     */
    private NonceProperties nonce;

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

    public String getNotBeforePaddingMilliseconds(){

        return notBeforePaddingMilliseconds;
    }

    public void setNotBeforePaddingMilliseconds(final String notBeforePaddingMilliseconds){

        this.notBeforePaddingMilliseconds = notBeforePaddingMilliseconds;
    }

    public NonceProperties getNonce(){

        return nonce;
    }

    public void setNonce(final NonceProperties nonce){

        this.nonce = nonce;
    }
}
