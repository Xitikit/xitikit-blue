package org.xitikit.blue.kit.authentication.v2dot0.interfaces;

import org.xitikit.blue.kit.authentication.v2dot0.Nonce;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Useful service for generating and validating nonces.
 *
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
public interface NonceStore{

    /**
     * Store objects that implement the Nonce interface.
     *
     * @param nonce @Nonnull Nonce
     */
    void put(@Nonnull Nonce nonce);

    /**
     * Removes and returns the Nonce matching the passed in nonceValue.
     * The Nonce should also be removed from the store.
     *
     * @param nonceValue @Nonnull String
     *
     * @return the nonce matching the passed in nonceValue.
     */
    @Nullable
    Nonce get(String nonceValue);

    /**
     * This should only purge those nonce values that are no longer valid,
     * but it is up to the implementation to decide how validity is determined.
     */
    void purge();
}
