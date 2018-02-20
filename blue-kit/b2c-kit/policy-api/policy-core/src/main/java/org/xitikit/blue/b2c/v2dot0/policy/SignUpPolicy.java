package org.xitikit.blue.b2c.v2dot0.policy;

/**
 * This class contains the configuration for the
 * built-in "Sign-up" B2C policies.
 *
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
public class SignUpPolicy implements PolicyForB2C{

    /**
     * This is the exact name of the policy web flow given after it was created.
     * This will often be the name you typed in when configuring the policy, but with
     * a prefix of "B2C_1_" added to it to indicate the version of that policy.
     */
    private String name;

    /**
     * This is the registered redirect url inside of Azure for the named policy web flow.
     * The value will be passed along with the request when redirecting the user to Azure.
     * When the user finished authentication, it will be the url to which she is sent.
     */
    private String redirect;

    /**
     * Optional. If you have configured azure to use your custom HTML and CSS, this is the
     * endpoint where the template resource can be found. Remember that JAvaScript is not allowed.
     */
    private String templateUrl;

    /**
     * Optional. Indicates that this policy is NOT to be used if set to true.
     */
    private boolean disabled;

    /**
     * Default no-arg constructor.
     * String values will default to null, while
     * disabled will default to false.
     */
    public SignUpPolicy(){

    }

    /**
     * All-arg constructor.
     *
     * @param name {@see SignUpPolicy.name}
     * @param redirectUrl {@see SignUpPolicy.redirectUrl}
     * @param templateUrl {@see SignUpPolicy.templateUrl}
     * @param disabled {@see SignUpPolicy.disabled}
     */
    public SignUpPolicy(final String name, final String redirectUrl, final String templateUrl, final boolean disabled){

        this.name = name;
        this.redirect = redirectUrl;
        this.templateUrl = templateUrl;
        this.disabled = disabled;
    }

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

        return redirect;
    }

    @Override
    public void setRedirectUrl(final String redirect){

        this.redirect = redirect;
    }

    @Override
    public String getTemplateUrl(){

        return templateUrl;
    }

    @Override
    public void setTemplateUrl(final String templateUrl){

        this.templateUrl = templateUrl;
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