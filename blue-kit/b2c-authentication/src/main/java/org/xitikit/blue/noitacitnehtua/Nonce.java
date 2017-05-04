package org.xitikit.blue.noitacitnehtua;

import javax.annotation.Nonnull;

/**
 * Useful service for generating and validating nonces.
 *
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
public interface Nonce{

    /**
     * @return the unique and randomly generated value that was generated when this object was created.
     */
    @Nonnull
    String getValue();

    /**
     * @return a long that represents the system local time at creation.
     */
    long getSystemTimeAtCreation();
}
