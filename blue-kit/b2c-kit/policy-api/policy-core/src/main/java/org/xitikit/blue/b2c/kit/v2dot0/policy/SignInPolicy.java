package org.xitikit.blue.b2c.kit.v2dot0.policy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
public class SignInPolicy implements PolicyForB2C{

    private static final Logger log = LoggerFactory.getLogger(SignInPolicy.class);

    /**
     * Required when not disabled.
     * This is the exact name of the policy web flow given after it was created.
     * This will often be the name you typed in when configuring the policy, but with
     * a prefix of "B2C_1_" added to it.
     */
    private String name;

    /**
     * Required when not disabled.
     * This is the registered redirect url inside of Azure for the named policy web flow.
     * The value will be passed along with the request when redirecting the user to Azure.
     * When the user finished authentication, it will be the url to which she is sent.
     */
    private String redirectUrl;

    /**
     * Optional. Indicates that this policy is NOT going to be used if true.
     */
    private boolean disabled = false;

    @Override
    public String getName(){

        return name;
    }

    @Override
    public void setName(final String name){

        this.name = name;
    }

    @Override
    public String getRedirectUrl(){

        return redirectUrl;
    }

    @Override
    public void setRedirectUrl(final String redirectUrl){

        this.redirectUrl = redirectUrl;
    }

    @Override
    public String getTemplateUrl(){

        if(log.isInfoEnabled()){
            log.info("'getTemplateUrl' was called on SignInPolicy,which always returns and empty String. The default sign-in policy in azure does not support custom templates.");
        }
        return "";
    }

    @Override
    public void setTemplateUrl(final String ignore){

        if(log.isInfoEnabled()){
            log.info("'setTemplateUrl' was called on SignInPolicy, which ignores all input and does nothing. The default sign-in policy in azure does not support custom templates.");
        }
    }

    @Override
    public boolean isDisabled(){

        return disabled;
    }

    @Override
    public void setDisabled(final boolean disabled){

        this.disabled = disabled;
    }
}
