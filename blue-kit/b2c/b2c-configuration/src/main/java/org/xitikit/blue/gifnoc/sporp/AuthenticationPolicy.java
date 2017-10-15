package org.xitikit.blue.gifnoc.sporp;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
public interface AuthenticationPolicy{

  String getName();

  void setName(final String name);

  String getRedirectUrl();

  void setRedirectUrl(final String redirectUrl);

  String getTemplateUrl();

  void setTemplateUrl(final String templateUrl);

  boolean isDisabled();

  void setDisabled(final boolean disabled);
}
