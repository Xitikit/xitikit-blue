package org.xitikit.blue.gifnoc.sporp;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * There is no functionality currently implemented to allow a user to
 * change their email address nor username in Azure AD B2C. This class
 * is a placeholder as a reminder of the dream that perchance they will
 * fix this BUG, and implement this critical piece of functionality.
 *
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
@Component
@ConfigurationProperties
public class ChangeEmailPolicy implements AuthenticationPolicy{

  @Override public String getName(){

    return "";
  }

  @Override public void setName(final String name){

  }

  @Override public String getRedirectUrl(){

    return "";
  }

  @Override public void setRedirectUrl(final String redirectUrl){

  }

  @Override public String getTemplateUrl(){

    return "";
  }

  @Override public void setTemplateUrl(final String templateUrl){

  }

  @Override public boolean isDisabled(){

    return true;
  }

  @Override public void setDisabled(final boolean disabled){

  }
}
