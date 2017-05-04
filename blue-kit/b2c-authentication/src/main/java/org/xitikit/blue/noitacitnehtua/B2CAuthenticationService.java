package org.xitikit.blue.noitacitnehtua;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * This class defines methods for interacting with AzureB2C.
 *
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
public interface B2CAuthenticationService{

    /**
     * Returns the sign-up URL for Azure.
     *
     * @return the URL
     */
    @Nonnull
    String getSignUpUrl();

    /**
     * Returns the sign-in URL for Azure.
     *
     * @return the URL
     */
    @Nonnull
    String getSignInUrl();

    /**
     * Returns the sign-up-or-sign0in URL for Azure.
     *
     * @return the URL
     */
    @Nonnull
    String getSignUpOrSignInUrl();

    /**
     * Returns the sign-out URL for Azure.
     *
     * @return the URL
     */
    @Nonnull
    String getSignOutUrl();

    /**
     * Returns the profile URL for Azure.
     *
     * @return the URL
     */
    @Nonnull
    String getProfileUrl();

    /**
     * Returns the reset password URL for Azure.
     *
     * @return the URL
     */
    @Nonnull
    String getResetPasswordUrl();

    /**
     * Extracts user data from the JWT token and validates it.
     *
     * @param idToken the token
     *
     * @return the extracted user data or null if the token could not be read or is invalid
     */
    @Nullable
    BlueWebToken decodeAndVerify(@Nonnull String idToken);

}
