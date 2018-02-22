package org.xitikit.blue.b2c.v2dot0.policy;

/**
 * Holds the configuration properties for the built in Azure
 * authentication policies.
 *
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
public class PolicyConfiguration{

    /**
     * Optional. It is recommended that developers only use the default value, but it
     * allows for those exceptions when there is no other choice but to use custom values.
     *
     * Default: {@link org.xitikit.blue.b2c.v2dot0.policy.PolicyUrlUtil.Defaults}.POLICY_BASE
     *
     * This is the relative base path used for all policy related requests made to the local application.
     * The value should always start with '/', and never end with '/'.
     *
     * The value could be blank to represent a path relative to the applications context-path, but that is not
     * recommended, even when the application only deals with policy related requests.
     */
    private String basePath = PolicyUrlUtil.Defaults.POLICY_BASE;

    /**
     * Contains the configuration for the built-in "edit-profile" B2C policies.
     */
    private EditProfilePolicy editProfile;

    /**
     * Contains the configuration for the built-in "reset-password" B2C policies.
     */
    private ResetPasswordPolicy resetPassword;

    /**
     * Contains the configuration for the built-in "sign-in" B2C policies.
     */
    private SignInPolicy signIn;

    /**
     * Contains the configuration needed to invalidate a users Azure authentication token.
     * This is treated as if it were an "Azure AD B2C" policy, but unless a custom policy
     * was created using the "Identity Experience Framework", it will normally just be
     * a specifically formatted request to either the "sign-in" or "sign-up-or-sign-in"
     * policy that was used to authenticate the user.
     */
    private SignOutPolicy signOut;

    /**
     * Contains the configuration for the built-in "sign-up" B2C policies.
     */
    private SignUpPolicy signUp;

    /**
     * Contains the configuration for the built-in "sign-up-or-sign-in" B2C policies.
     */
    private SignUpOrSignInPolicy signUpOrSignIn;

    /**
     * Default no-arg
     */
    public PolicyConfiguration(){

    }

    /**
     * Default all-arg
     */
    public PolicyConfiguration(
        final String basePath,
        final EditProfilePolicy editProfile,
        final ResetPasswordPolicy resetPassword,
        final SignInPolicy signIn,
        final SignOutPolicy signOut,
        final SignUpPolicy signUp,
        final SignUpOrSignInPolicy signUpOrSignIn){

        // The basePath is an exception to the standard WYSIWYG
        // practice in models and property/configuration classes.
        this.basePath = PolicyUrlUtil.checkPolicyBasePath(basePath);
        this.signUp = signUp;
        this.signIn = signIn;
        this.signUpOrSignIn = signUpOrSignIn;
        this.resetPassword = resetPassword;
        this.editProfile = editProfile;
        this.signOut = signOut;
    }

    public String getBasePath(){

        return basePath;
    }

    public void setBasePath(final String basePath){

        this.basePath = basePath;
    }

    public EditProfilePolicy getEditProfile(){

        return editProfile;
    }

    public void setEditProfile(final EditProfilePolicy editProfile){

        this.editProfile = editProfile;
    }

    public ResetPasswordPolicy getResetPassword(){

        return resetPassword;
    }

    public void setResetPassword(final ResetPasswordPolicy resetPassword){

        this.resetPassword = resetPassword;
    }

    public SignInPolicy getSignIn(){

        return signIn;
    }

    public void setSignIn(final SignInPolicy signIn){

        this.signIn = signIn;
    }

    public SignOutPolicy getSignOut(){

        return signOut;
    }

    public void setSignOut(final SignOutPolicy signOut){

        this.signOut = signOut;
    }

    public SignUpPolicy getSignUp(){

        return signUp;
    }

    public void setSignUp(final SignUpPolicy signUp){

        this.signUp = signUp;
    }

    public SignUpOrSignInPolicy getSignUpOrSignIn(){

        return signUpOrSignIn;
    }

    public void setSignUpOrSignIn(final SignUpOrSignInPolicy signUpOrSignIn){

        this.signUpOrSignIn = signUpOrSignIn;
    }
}
