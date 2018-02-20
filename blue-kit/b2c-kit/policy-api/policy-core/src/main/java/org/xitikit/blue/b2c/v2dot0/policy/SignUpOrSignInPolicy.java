package org.xitikit.blue.b2c.v2dot0.policy;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
public class SignUpOrSignInPolicy implements PolicyForB2C{

    /**
     * This is the exact name of the policy web flow given after it was created.
     * This will often be the name you typed in when configuring the policy, but with
     * a prefix of "B2C_1_" added to it.
     */
    private String name;

    /**
     * This is the registered redirect url inside of Azure for the named policy web flow.
     * The value will be passed along with the request when redirecting the user to Azure.
     * When the user finished authentication, it will be the url to which she is sent.
     */
    private String redirectUrl;

    /**
     * Optional. If you have configured azure to use your custom HTML and CSS, this is the
     * endpoint where the template resource can be found. Remember that JAvaScript is not allowed.
     */
    private String templateUrl;

    /**
     * Optional. Indicates that this policy is NOT going to be used if true.
     */
    private boolean disabled = false;

    /**
     * Default no-arg constructor.
     * String values will default to null, while
     * disabled will default to false.
     */
    public SignUpOrSignInPolicy(){

    }

    /**
     * All-arg constructor.
     *
     * @param name {@see SignUpPolicy.name}
     * @param redirectUrl {@see SignUpPolicy.redirect}
     * @param templateUrl {@see SignUpPolicy.templateUrl}
     * @param disabled {@see SignUpPolicy.disabled}
     */
    public SignUpOrSignInPolicy(final String name, final String redirectUrl, final String templateUrl, final boolean disabled){

        this.name = name;
        this.redirectUrl = redirectUrl;
        this.templateUrl = templateUrl;
        this.disabled = disabled;
    }

    public String getName(){

        return name;
    }

    public void setName(final String name){

        this.name = name;
    }

    public String getRedirectUrl(){

        return redirectUrl;
    }

    public void setRedirectUrl(final String redirectUrl){

        this.redirectUrl = redirectUrl;
    }

    public String getTemplateUrl(){

        return templateUrl;
    }

    public void setTemplateUrl(final String templateUrl){

        this.templateUrl = templateUrl;
    }

    public boolean isDisabled(){

        return disabled;
    }

    public void setDisabled(final boolean disabled){

        this.disabled = disabled;
    }
}
