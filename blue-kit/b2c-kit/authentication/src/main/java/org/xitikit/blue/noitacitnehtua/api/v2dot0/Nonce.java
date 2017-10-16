package org.xitikit.blue.noitacitnehtua.api.v2dot0;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * Represents a unique token used to ensure that
 * authentication attempts only occur once,and not more.
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
