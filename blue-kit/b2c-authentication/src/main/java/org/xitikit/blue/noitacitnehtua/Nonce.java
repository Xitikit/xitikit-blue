package org.xitikit.blue.noitacitnehtua;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * Represents a
 *
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
public final class Nonce{

    private final long systemTimeAtCreation = System.currentTimeMillis();
    private final String value = UUID.randomUUID().toString();

    @Nonnull
    public String getValue(){

        return value;
    }

    public long getSystemTimeAtCreation(){

        return systemTimeAtCreation;
    }
}
