package org.xitikit.azureadb2c.common.errors;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * @author J. Keith Hoopes
 *         Copyright Xitikit.org 2017
 */
public class MethodNotAllowedExceptionTest {

    @SuppressWarnings("ConstantConditions")
    @Test
    public void verify() {

        MethodNotAllowedException e = new MethodNotAllowedException();
        assertTrue(e.getArguments() != null);

        assertTrue(e instanceof MessageSourceResolvableException);
        assertTrue(e instanceof RuntimeException);
    }
}