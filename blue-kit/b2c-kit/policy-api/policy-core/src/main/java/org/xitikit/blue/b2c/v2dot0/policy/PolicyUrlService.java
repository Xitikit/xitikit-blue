package org.xitikit.blue.b2c.v2dot0.policy;

import javax.annotation.Nonnull;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
public interface PolicyUrlService{

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
