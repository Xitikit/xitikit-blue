package org.xitikit.blue.kit.authentication.v2dot0.interfaces;

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
