package org.xitikit.azureadb2c.common.errors;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * @author J. Keith Hoopes
 *         Copyright Xitikit.org 2017
 */
public class NotFoundExceptionTest {

    @SuppressWarnings("ConstantConditions")
    @Test
    public void verify() {

        NotFoundException e = new NotFoundException();
        assertTrue(e instanceof MessageSourceResolvableException);
        assertTrue(e instanceof RuntimeException);

        assertTrue(e.getArguments() != null);

        e = new NotFoundException("Test Input");
        assertTrue(e.getArguments() != null);

        e = new NotFoundException("Test Input", new RuntimeException());
        assertTrue(e.getArguments() != null);
    }
}