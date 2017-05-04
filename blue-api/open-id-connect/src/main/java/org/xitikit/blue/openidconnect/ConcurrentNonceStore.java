package org.xitikit.blue.openidconnect;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Useful service for generating and validating nonces.
 *
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
public class ConcurrentNonceStore implements NonceStore{

    private final Map<String, Nonce> nonceStore = new ConcurrentHashMap<>();

    @Override
    public void put(@Nonnull Nonce nonce) {

        nonceStore.put(nonce.getValue(), nonce);
    }

    @Override
    public Nonce get(@Nonnull String nonceValue) {

        return nonceStore.get(nonceValue);
    }
}
