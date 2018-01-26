package org.xitikit.blue.b2c.kit.v2dot0.authentication.interfaces;

import javax.annotation.Nonnull;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
public interface UrlService{

  @Nonnull
  String getSignUpUrl();

  @Nonnull
  String getSignInUrl();

  @Nonnull
  String getSignUpOrSignInUrl();

  @Nonnull
  String getProfileUrl();

  @Nonnull
  String getResetPasswordUrl();

  @Nonnull
  String getSignOutUrl();

  String wellKnownEndpoint(String policy);
}
