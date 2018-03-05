package org.xitikit.blue.b2c.v2dot0.policy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Contains the configuration for users to be able to "sign out"
 * of the Azure AD B2C tenant, and invalidating their authentication.
 * <p>
 * The Azure AD B2C "sign-out" policies will never have a template
 * associated with them. The purpose is to invalidate the users
 * authentication token, and then redirect them back to the
 * application or some other "home" page that does not require
 * authentication to reach. In the case of built-in policies, the
 * xitikit-blue specific "sign-out" policy is really just a redirect
 * to whichever policy was used to authenticate the user. For design
 * and consistency, this api treats this interaction as if it were a
 * separate policy.
 * <p>
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
public class SignOutPolicy implements PolicyForB2C{

    private static final Logger log = LoggerFactory.getLogger(SignOutPolicy.class);

    /**
     * Optional. It is recommended that developers only use the default value, but it
     * allows for those exceptions when there is no other choice but to use custom values.
     * <p>
     * Default: {@link PolicyUrlUtil.Defaults}.RESET_PASSWORD_BASE
     * <p>
     * If the value that is set for the {@link SignOutPolicy} 'basePath' property
     * is blank, then it will use the default value. This includes the default value for
     * the {@link PolicyConfiguration} 'basePath', while any custom value will be ignored.
     * <p>
     * This is the path relative to the applications context-path
     * that is used for all {@link SignOutPolicy} related requests made
     * to the local application. The value should always start with '/',
     * and never end with '/'.
     * <p>
     * Warning: Do NOT set this value to be blank nor '/', or you may see
     * some unexpected behaviour.
     */
    private String basePath;

    /**
     * Optional.
     * <p>
     * There is not an authentication policy specific to {@link SignOutPolicy}. Instead,
     * you will use the the name of the policy that you used when authenticating
     * a user (either the SignInPolicy or the SignUpOrSignInPolicy). If a value is
     * not specified, then it will first try to use the SignUpOrSignInPolicy name if it is
     * not null/empty and it is not disabled, then it will try to use the SignInPolicy name
     * under the same conditions.
     * <p>
     * If you are using a custom policy built with the Identity Experience Framework, then you
     * will need to set this value to the name of the policy that you created for handling
     * the sign out.
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

    /**
     * Default no-arg constructor.
     * String values will default to null, while
     * disabled will default to false.
     * <p>
     * Note: The Azure AD B2C "sign-out" policies will never
     * have a template associated with them. Their purpose is
     * to invalidate the users authentication token, and the
     * redirect the user back to the application or some other
     * "home" page that does not require authentication to reach.
     */
    public SignOutPolicy(){

    }

    /**
     * All-arg constructor.
     *
     * @param name {@see SignUpPolicy.name}
     * @param redirectUrl {@see SignUpPolicy.redirect}
     * @param disabled {@see SignUpPolicy.disabled}
     */
    public SignOutPolicy(final String basePath, final String name, final String redirectUrl, final boolean disabled){

        this.basePath = basePath;
        this.name = name;
        this.redirectUrl = redirectUrl;
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
    public void setRedirectUrl(final String redirectUrl){

        this.redirectUrl = redirectUrl;
    }

    /**
     * The Azure AD B2C "sign-out" policies will never
     * have a template associated with them. Their purpose is
     * to invalidate the users authentication token, and the
     * redirect the user back to the application or some other
     * "home" page that does not require authentication to reach.
     *
     * @return null
     */
    @Override
    public String getTemplateUrl(){

        if(log.isDebugEnabled()){
            log.debug("'getTemplateUrl' was called on SignOutPolicy, which always returns null. The default sign-in policy in azure does not support custom templates.");
        }
        return null;
    }

    /**
     * The Azure AD B2C "sign-out" policies will never
     * have a template associated with them. Their purpose is
     * to invalidate the users authentication token, and the
     * redirect the user back to the application or some other
     * "home" page that does not require authentication to reach.
     *
     * @param ignored {@link String}: The input is always ignored.
     */
    @Override
    public void setTemplateUrl(final String ignored){

        if(log.isDebugEnabled()){
            log.debug("'setTemplateUrl' was called on SignOutPolicy, which ignores all input and does nothing. The default sign-in policy in azure does not support custom templates.");
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
