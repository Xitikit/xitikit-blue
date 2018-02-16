package org.xitikit.blue.boot.b2c;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.xitikit.blue.b2c.kit.v2dot0.policy.*;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
@Component
@ConfigurationProperties("xitikit.blue.kit.b2c.policy")
public class SimplePolicyConfiguration{

    private SignUpPolicy signUp;

    private SignInPolicy signIn;

    private SignUpOrSignInPolicy signUpOrSignIn;

    private ResetPasswordPolicy resetPassword;

    private EditProfilePolicy editProfile;

    private SignOutPolicy signOut;

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
