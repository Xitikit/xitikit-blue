package org.xitikit.blue.noitacitnehtua.api.v2dot0.interfaces;

import javax.annotation.Nonnull;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
public interface UrlService{

  @Nonnull String getSignUpUrl();

  @Nonnull String getSignInUrl();

  @Nonnull String getSignUpOrSignInUrl();

  @Nonnull String getProfileUrl();

  @Nonnull String getResetPasswordUrl();

  @Nonnull String getSignOutUrl();

  String wellKnownEndpoint(String policy);
}
