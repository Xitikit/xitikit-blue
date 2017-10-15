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
public class ResetPasswordPolicy implements AuthenticationPolicy{

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

  @Override public String getName(){

    return name;
  }

  @Override public void setName(final String name){

    this.name = name;
  }

  @Override public String getRedirectUrl(){

    return redirectUrl;
  }

  @Override public void setRedirectUrl(final String redirectUrl){

    this.redirectUrl = redirectUrl;
  }

  @Override public String getTemplateUrl(){

    return templateUrl;
  }

  @Override public void setTemplateUrl(final String templateUrl){

    this.templateUrl = templateUrl;
  }

  @Override public boolean isDisabled(){

    return disabled;
  }

  @Override public void setDisabled(final boolean disabled){

    this.disabled = disabled;
  }
}
