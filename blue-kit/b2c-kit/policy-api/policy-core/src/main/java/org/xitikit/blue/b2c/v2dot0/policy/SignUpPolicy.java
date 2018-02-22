package org.xitikit.blue.b2c.v2dot0.policy;

/**
 * This class contains the configuration for a built in Azure AD B2C 'sign-up' policy.
 * <p>
 * https://docs.microsoft.com/en-us/azure/active-directory-b2c/active-directory-b2c-reference-policies#create-a-sign-up-policy
 * <p>
 * The properties of this class should be inherently WYSIWYG, but the autoconfiguration
 * of the policy-boot module should contain logic for certain default values of the
 * fields.
 * <p>
 * Refer to the documentation for each individual field for an understanding of how
 * this class is used.
 * <p>
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */

public class SignUpPolicy implements PolicyForB2C{

    /**
     * Optional.
     * <p>
     * Default: {@link PolicyUrlUtil.Defaults}.SIGN_UP_OR_SIGN_IN_BASE
     * <p>
     * It is recommended that developers only use the default value, but it
     * allows for those exceptions when there is no other choice but to use custom values.
     * <p>
     * If the value that is set for the {@link SignUpOrSignInPolicy} 'basePath' property
     * is blank, then it will use the default value. This includes the default value for
     * the {@link PolicyConfiguration} 'basePath', while any custom value will be ignored.
     * <p>
     * This is the path relative to the applications context-path
     * that is used for all {@link SignUpOrSignInPolicy} related requests made
     * to the local application. The value should always start with '/',
     * and never end with '/'.
     * <p>
     * The value that is set for the 'basePath' property in the {@link PolicyConfiguration}
     * is ignored when a non-blank value is provided here. If you include your own value,
     * it must be relative to the applications context-path.
     * <p>
     * Warning: Do NOT set this value to be blank nor '/', or you may see
     * some unexpected behaviour.
     */
    private String basePath = PolicyUrlUtil.Defaults.SIGN_UP_BASE;

    /**
     * This is the exact name of the policy web flow given after it was created.
     * This will often be the name you typed in when configuring the policy, but with
     * a prefix of "B2C_1_" added to it to indicate the version of that policy.
     */
    private String name;

    /**
     * This is the registered redirectUrl url inside of Azure for the named policy web flow.
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
        this.redirectUrl = redirectUrl;
        this.templateUrl = templateUrl;
        this.disabled = disabled;
    }

    @Override
    public String getBasePath(){

        return basePath;
    }

    @Override
    public void setBasePath(final String basePath){

        this.basePath = basePath;
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

        return redirectUrl;
    }

    @Override
    public void setRedirectUrl(final String redirect){

        this.redirectUrl = redirect;
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
