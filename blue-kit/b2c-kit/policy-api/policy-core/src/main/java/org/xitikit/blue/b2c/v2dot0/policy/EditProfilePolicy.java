package org.xitikit.blue.b2c.v2dot0.policy;

/**
 * This class contains the configuration for a built in Azure AD B2C 'edit-profile' policy.
 * <p>
 * https://docs.microsoft.com/en-us/azure/active-directory-b2c/active-directory-b2c-reference-policies#create-a-profile-editing-policy
 * <p>
 * The properties of this class should be inherently WYSIWYG, but the autoconfiguration
 * of the policy-boot module should contain logic for certain default values of the
 * fields.
 * <p>
 * Refer to the documentation for each individual field for an understanding of how
 * this class is used.
 * <p>
 * Copyright Xitikit.org ${year}
 *
 * @author J. Keith Hoopes
 */
public class EditProfilePolicy implements PolicyForB2C{

    /**
     * Optional. It is recommended that developers only use the default value, but it
     * allows for those exceptions when there is no other choice but to use custom values.
     * <p>
     * Default: {@link PolicyUrlUtil}.{@link org.xitikit.blue.b2c.v2dot0.policy.PolicyUrlUtil.Defaults}.EDIT_PROFILE_BASE
     * <p>
     * If the value that is set for the {@link EditProfilePolicy} 'basePath' property
     * is blank, then it will use the default value (including the default value for
     * the {@link PolicyConfiguration} 'basePath'.
     * <p>
     * This is the path relative to the applications context-path
     * that is used for all EditProfilePolicy related requests made
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
    private String basePath = PolicyUrlUtil.Defaults.EDIT_PROFILE_BASE;

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
     * Optional. If you have configured azure to use your custom HTML and CSS, this is the
     * endpoint where the template resource can be found. Remember that JAvaScript is not allowed.
     */
    private String templateUrl;

    /**
     * Optional. Indicates that this policy is NOT going to be used if true.
     */
    private boolean disabled = false;

    public EditProfilePolicy(){

    }

    public EditProfilePolicy(final String basePath, final String name, final String redirectUrl, final String templateUrl, final boolean disabled){

        this.basePath = PolicyUrlUtil.checkEditProfilePath(basePath);
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
    public void setRedirectUrl(final String redirectUrl){

        this.redirectUrl = redirectUrl;
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
