package org.xitikit.blue.b2c.v2dot0.policy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class contains the configuration for a built in Azure AD B2C 'sign-in' policy.
 * <p>
 * https://docs.microsoft.com/en-us/azure/active-directory-b2c/active-directory-b2c-reference-policies#create-a-sign-in-policy
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
public class SignInPolicy implements PolicyForB2C{

    private static final Logger log = LoggerFactory.getLogger(SignInPolicy.class);

    /**
     * Optional.
     * <p>
     * Default: {@code {@link PolicyUrlUtil.Defaults}.SIGN_IN_BASE}
     * <p>
     * Developers should normally only use the default value, but the core module
     * allows for those exceptions when there is no other choice but to use custom values.
     * The logic for this, however, is only in the policy-boot module. If only the policy-core
     * module is being used, then this field should be WYSIWYG. The following is a set of
     * guidelines that should be followed by the policy-boot module when handling values in
     * this configuration.
     * <p>
     * If the value that is set for the {@link SignInPolicy} 'basePath' property
     * is blank, then it  should use the default value of {@link PolicyUrlUtil.Defaults}.RESET_PASSWORD_BASE
     * <p>
     * This path must be relative to the applications context-path. It is used for all basic {@link SignInPolicy}
     * related requests made to the local application. The value should always start with '/', and never end
     * with '/'. This also means that a value of exactly '/' is not allowed either. This will, by intent, prevent
     * the base path for this policy from being set to the root application context.
     * <p>
     * Note: You may notice that the default values all have a relative base of '/blue-kit/policy'. '/blue-kit' is
     * the base relative path for all autoconfigured request mappings for this project. Since this module is part
     * of the 'policy-api' subsystem, and is a child of the 'blue-kit' base, the baseUrl for all autoconfigured
     * policy request mappings is '/blue-kit/policy' + '/${policy-type)'. For example, 'sign-in' policies will
     * all have a baseUrl of '/blue-kit/policy/sign-in'.
     * <p>
     * Warning: Do NOT set this value to be blank nor '/', or you may see
     * some unexpected behaviour.
     */
    private String basePath;

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

    /**
     * default no-arg
     */
    public SignInPolicy(){

    }

    /**
     * Default all-arg
     */
    public SignInPolicy(
        final String basePath,
        final String name,
        final String redirectUrl,
        final boolean disabled){

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
     * The Azure AD B2C "sign-in" policies will never
     * have a template associated with them. If you
     * need a custom "sign-in" experience, look into
     * either using the built-in "sign-up-or-sign-in"
     * policy, or create your own custom policy with the
     * Identity Experience Framework.
     * <p>
     * https://docs.microsoft.com/en-us/azure/active-directory-b2c/active-directory-b2c-overview-custom
     *
     * @return null
     */
    @Override
    public String getTemplateUrl(){

        if(log.isDebugEnabled()){
            log.debug("'getTemplateUrl' was called on SignInPolicy, which always returns null. The default sign-in policy in azure does not support custom templates.");
        }
        return null;
    }

    /**
     * The Azure AD B2C "sign-in" policies will never
     * have a template associated with them. If you
     * need a custom "sign-in" experience, look into
     * either using the built-in "sign-up-or-sign-in"
     * policy, or create your own custom policy with the
     * Identity Experience Framework.
     * <p>
     * https://docs.microsoft.com/en-us/azure/active-directory-b2c/active-directory-b2c-overview-custom
     *
     * @param ignored Is always ignored.
     */
    @Override
    public void setTemplateUrl(final String ignored){

        if(log.isDebugEnabled()){
            log.debug("'setTemplateUrl' was called on SignInPolicy, which ignores all input and does nothing. The default sign-in policy in azure does not support custom templates.");
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
