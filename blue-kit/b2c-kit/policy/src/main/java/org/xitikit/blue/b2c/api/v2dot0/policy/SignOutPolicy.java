package org.xitikit.blue.b2c.api.v2dot0.policy;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
@Component
@ConfigurationProperties
public class SignOutPolicy implements AuthenticationPolicy{

  /**
   * Optional.
   * There is NOT an authentication policy specific to SignOut. Instead,
   * you will use the the name of the policy that you used when authenticating
   * a user (either the SignInPolicy or the SignUpOrSignInPolicy). If a value is
   * not specified, then it will first try to use the SignUpOrSignInPolicy name if it is
   * not null/empty and it is not disabled, then it will try to use the SignInPolicy name
   * under the same conditions.
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

    return "";
  }

  @Override
  public void setTemplateUrl(final String templateUrl){

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
