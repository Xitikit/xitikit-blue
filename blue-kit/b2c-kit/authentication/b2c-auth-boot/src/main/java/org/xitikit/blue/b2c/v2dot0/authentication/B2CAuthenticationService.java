package org.xitikit.blue.b2c.v2dot0.authentication;

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
     * Extracts user data from the JWT token and validates it.
     *
     * @param idToken the token
     *
     * @return the extracted user data or null if the token could not be read or is invalid
     */
    @Nullable
    BlueWebToken decodeAndVerify(@Nonnull final String idToken);
}
