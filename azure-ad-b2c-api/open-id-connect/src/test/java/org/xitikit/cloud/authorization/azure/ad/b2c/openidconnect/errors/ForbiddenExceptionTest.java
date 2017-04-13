package org.xitikit.blue.authorization.azure.ad.b2c.openidconnect.errors;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * @author J. Keith Hoopes
 *         Copyright Xitikit.org 2017
 */
public class ForbiddenExceptionTest {

    @SuppressWarnings("ConstantConditions")
    @Test
    public void verify() {

        ForbiddenException e = new ForbiddenException();
        assertTrue(e.getArguments() != null);

        e = new ForbiddenException("Test Input");
        assertTrue(e.getArguments() != null);

        assertTrue(e instanceof MessageSourceResolvableException);
        assertTrue(e instanceof RuntimeException);
    }
}