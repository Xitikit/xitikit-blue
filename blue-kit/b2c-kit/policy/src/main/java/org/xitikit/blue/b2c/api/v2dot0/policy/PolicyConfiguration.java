package org.xitikit.blue.b2c.api.v2dot0.policy;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.xitikit.blue.b2c.api.v2dot0.policy.*;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
@Component
@ConfigurationProperties
public class PolicyConfiguration{

  private SignUpPolicy signUpPolicy;

  private SignInPolicy signInPolicy;

  private SignUpOrSignInPolicy signUpOrSignInPolicy;

  private ResetPasswordPolicy resetPasswordPolicy;

  private EditProfilePolicy editProfilePolicy;

  private SignOutPolicy signOutPolicy;

  public SignUpPolicy getSignUpPolicy(){

    return signUpPolicy;
  }

  public SignInPolicy getSignInPolicy(){

    return signInPolicy;
  }

  public SignUpOrSignInPolicy getSignUpOrSignInPolicy(){

    return signUpOrSignInPolicy;
  }

  public ResetPasswordPolicy getResetPasswordPolicy(){

    return resetPasswordPolicy;
  }

  public EditProfilePolicy getEditProfilePolicy(){

    return editProfilePolicy;
  }

  public SignOutPolicy getSignOutPolicy(){

    return signOutPolicy;
  }
}
