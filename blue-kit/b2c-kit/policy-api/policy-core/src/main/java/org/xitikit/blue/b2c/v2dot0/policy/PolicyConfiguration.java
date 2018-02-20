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

    private SignUpPolicy signUp;

    private SignInPolicy signIn;

    private SignUpOrSignInPolicy signUpOrSignIn;

    private ResetPasswordPolicy resetPassword;

    private EditProfilePolicy editProfile;

    private SignOutPolicy signOut;

    public PolicyConfiguration(){

    }

    public PolicyConfiguration(
        final SignUpPolicy signUp,
        final SignInPolicy signIn,
        final SignUpOrSignInPolicy signUpOrSignIn,
        final ResetPasswordPolicy resetPassword,
        final EditProfilePolicy editProfile,
        final SignOutPolicy signOut){

        this.signUp = signUp;
        this.signIn = signIn;
        this.signUpOrSignIn = signUpOrSignIn;
        this.resetPassword = resetPassword;
        this.editProfile = editProfile;
        this.signOut = signOut;
    }

    public SignUpPolicy getSignUp(){

        return signUp;
    }

    public void setSignUp(final SignUpPolicy signUp){

        this.signUp = signUp;
    }

    public SignInPolicy getSignIn(){

        return signIn;
    }

    public void setSignIn(final SignInPolicy signIn){

        this.signIn = signIn;
    }

    public SignUpOrSignInPolicy getSignUpOrSignIn(){

        return signUpOrSignIn;
    }

    public void setSignUpOrSignIn(final SignUpOrSignInPolicy signUpOrSignIn){

        this.signUpOrSignIn = signUpOrSignIn;
    }

    public ResetPasswordPolicy getResetPassword(){

        return resetPassword;
    }

    public void setResetPassword(final ResetPasswordPolicy resetPassword){

        this.resetPassword = resetPassword;
    }

    public EditProfilePolicy getEditProfile(){

        return editProfile;
    }

    public void setEditProfile(final EditProfilePolicy editProfile){

        this.editProfile = editProfile;
    }

    public SignOutPolicy getSignOut(){

        return signOut;
    }

    public void setSignOut(final SignOutPolicy signOut){

        this.signOut = signOut;
    }
}
