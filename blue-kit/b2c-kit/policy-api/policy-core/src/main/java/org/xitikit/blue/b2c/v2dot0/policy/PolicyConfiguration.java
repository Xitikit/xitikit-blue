package org.xitikit.blue.b2c.v2dot0.policy;

/**
 * Holds the configuration properties for the built in Azure
 * authentication policies.
 * <p>
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
public class PolicyConfiguration{

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
        final EditProfilePolicy editProfile,
        final ResetPasswordPolicy resetPassword,
        final SignInPolicy signIn,
        final SignOutPolicy signOut,
        final SignUpPolicy signUp,
        final SignUpOrSignInPolicy signUpOrSignIn){

        this.signUp = signUp;
        this.signIn = signIn;
        this.signUpOrSignIn = signUpOrSignIn;
        this.resetPassword = resetPassword;
        this.editProfile = editProfile;
        this.signOut = signOut;
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
