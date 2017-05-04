package org.xitikit.blue.noitacitnehtua;

import javax.annotation.Nonnull;

/**
 * Useful service for generating and validating nonces.
 * <p>
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
    Nonce get(@Nonnull String nonceValue);

    /**
     * This should only purge those nonce values that are no longer valid,
     * but it is up to the implementation to decide how validity is determined.
     */
    void purge();
}
