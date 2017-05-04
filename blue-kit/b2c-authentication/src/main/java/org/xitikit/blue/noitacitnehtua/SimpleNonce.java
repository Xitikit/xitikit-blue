package org.xitikit.blue.noitacitnehtua;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * Useful service for generating and validating nonces.
 * <p>
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
public class SimpleNonce implements Nonce{

    private final long systemTimeAtCreation = System.currentTimeMillis();
    private final UUID value = UUID.randomUUID();

    @Nonnull
    @Override
    public String getValue(){

        return value.toString();
    }

    @Override
    public long getSystemTimeAtCreation(){

        return systemTimeAtCreation;
    }
}
