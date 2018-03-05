package org.xitikit.blue.b2c.v2dot0.policy;

import javax.annotation.Nullable;

/**
 * This interface defines the configuration for an Azure AD B2C policy.
 * <p>
 * https://docs.microsoft.com/en-us/azure/active-directory-b2c/active-directory-b2c-reference-policies
 * <p>
 * The "getters and setters" are there to define the fields that a policy should have,
 * following the standard java bean naming convention. The methods and fields should be
 * follow WYSIWYG (i.e., do not modify values that are passed in nor returned.
 * AutoConfiguration classes of the policy-boot module should handle logic for any
 * default values of the fields. *
 * <p>
 * Refer to the documentation for each individual field for an understanding of how
 * this class should be implemented.
 * <p>
 * Copyright Xitikit.org ${year}
 *
 * @author J. Keith Hoopes
 */
public interface PolicyForB2C{

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
    @Nullable
    String getBasePath();

    /**
     * Optional.
     * <p>
     * Default values should be defined as constants in "{@link PolicyUrlUtil.Defaults}".
     * <p>
     * Developers should normally only use the default value, but the core module
     * allows for those exceptions when there is no other choice but to use custom values.
     * The logic for this, however, is only in the policy-boot module. If only the policy-core
     * module is being used, then this field should be WYSIWYG. The following is a set of
     * guidelines that should be followed by the policy-boot module when handling values in
     * this configuration.
     * <p>
     * If the value that is set for the 'basePath' property is blank, then it  should use the
     * default value of the related constant in "{@link PolicyUrlUtil.Defaults}".
     * <p>
     * This path must be relative to the applications context-path. It is used for all basic {@link SignInPolicy}
     * related requests made to the local application. The value should always start with '/', and never end
     * with '/'. This also means that a value of exactly '/' is not allowed either. This will, by intent, prevent
     * the base path for this policy from being set to the root application context.
     * <p>
     * Default values should all have a relative base of '/blue-kit/policy'. '/blue-kit' is
     * the base relative path for all autoconfigured request mappings for the xitikit-blue project.
     * Since this module is part of the 'policy-api' subsystem, and is a child of the 'blue-kit'
     * epic, the baseUrl for all autoconfigured policy request mappings should be
     * '/blue-kit/policy' + '/${policy-type-name)'. For example, the built in 'sign-in'
     * policy will all have a baseUrl of '/blue-kit/policy/sign-in'.
     * <p>
     * TODO: Multiple policy configurations for a single built-in policy type are not currently supported.
     * It is recommended to only configure one policy type per application. If you find a
     * need or are otherwise required to configure multiple instances of the same policy
     * type then the basePath should be the default basePath plus a unique custom name
     * for each configuration.
     * <p>
     * Caution: Do NOT set this value to be blank nor '/', or you may see
     * some unexpected behaviour. For instance, the method
     * (@code PolicyUrlUtil.combineRelativePaths();} does not allow '/' paths, and will
     * throw an {@link IllegalArgumentException} if encountered.
     */
    void setBasePath(@Nullable String basePath);

    /**
     * @return {@link String}: the name of the
     *     configured or custom policy in Azure AD B2C.   *
     *     Example: "B2C_1_Xitikit_ChangeEmail"
     *
     * @see PolicyForB2C ::setName
     */
    @Nullable
    String getName();

    /**
     * @param name {@link String}: the name of the
     *     configured or custom policy in Azure AD B2C.   *
     *     <p>
     *     Example: "B2C_1_Xitikit_ChangeEmail"
     *
     * @see PolicyForB2C ::getName
     */
    void setName(final String name);

    /**
     * @return {@link String}: the url to be used to redirect
     *     the user that is currently on/in your website or
     *     application to the "Azure AD B2C" policy.
     *     <p>
     *     Example: "https://localhost:8443/change-email"
     *
     * @see PolicyForB2C ::getRedirectUrl
     */
    @Nullable
    String getRedirectUrl();

    /**
     * @param redirectUrl {@link String}: the url to be used
     *     to redirect user that is currently on/in you website or
     *     application to the Azure AD B2C policy.
     *     <p>
     *     Example: "https://localhost:8443/change-email"
     *
     * @see PolicyForB2C ::getRedirectUrl
     */
    void setRedirectUrl(@Nullable final String redirectUrl);

    /**
     * @return {@link String}: The url that will be used by the
     *     "Azure AD B2C" directory to retrieve the html that will be
     *     used for inserting the B2C elements, CSS, and JavaScript.
     *
     * @see PolicyForB2C ::setTemplateUrl
     */
    @Nullable
    String getTemplateUrl();

    /**
     * @param templateUrl {@link String}: The url that will be used
     *     by the "Azure AD B2C" directory to retrieve the html that will
     *     be used for inserting the B2C elements, CSS, and JavaScript.
     *
     * @see PolicyForB2C ::getTemplateUrl
     */
    void setTemplateUrl(@Nullable final String templateUrl);

    /**
     * @return true if this policy should not be used.
     */
    boolean isDisabled();

    /**
     * @param disabled Set to {@code true} if this policy should not be used.
     */
    void setDisabled(final boolean disabled);
}
