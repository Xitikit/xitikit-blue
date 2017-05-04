package org.xitikit.blue.openidconnect;

import javax.annotation.Nonnull;

/**
 * Useful service for generating and validating nonces.
 *
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
public interface NonceStore {

    void put(@Nonnull Nonce nonce);
    /**
     * @return a long that represents the system local time at creation.
     */
    Nonce get(@Nonnull String nonceValue);
}
