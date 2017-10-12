package org.xitikit.blue.gifnoc.sporp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
@Data
@Wither
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpPolicy{

  private String name;

  private String redirect;

  private String templateUrl;

  /**
   * This is the exact name of the policy web flow given after it was created.
   * This will often be the name you typed in when configuring the policy, but with
   * a prefix of "B2C_1_" added to it.
   */
  public String getName(){

    return name;
  }

  /**
   * This is the exact name of the policy web flow given after it was created.
   * This will often be the name you typed in when configuring the policy, but with
   * a prefix of "B2C_1_" added to it.
   */
  public void setName(String name){

    this.name = name;
  }

  /**
   * This is the registered redirect url inside of Azure for the named policy web flow.
   * The value will be passed along with the request when redirecting the user to Azure.
   * When the user finished authentication, it will be the url to which she is sent.
   */
  public String getRedirectUrl(){

    return redirect;
  }

  /**
   * This is the registered redirect url inside of Azure for the named policy web flow.
   * The value will be passed along with the request when redirecting the user to Azure.
   * When the user finished authentication, it will be the url to which she is sent.
   */
  public void setRedirectUrl(String redirect){

    this.redirect = redirect;
  }

  /**
   * Optional. If you have configured azure to use your custom HTML and CSS, this is the
   * endpoint where the template resource can be found. Remember that JAvaScript is not allowed.
   */
  public String getTemplateUrl(){

    return templateUrl;
  }

  /**
   * Optional. If you have configured azure to use your custom HTML and CSS, this is the
   * endpoint where the template resource can be found. Remember that JAvaScript is not allowed.
   */
  public void setTemplateUrl(String templateUrl){

    this.templateUrl = templateUrl;
  }
}
