package org.xitikit.blue.common.errors.test;

import org.junit.Test;
import org.xitikit.blue.nommoc.errors.ValidationFailedException;

import static junit.framework.TestCase.*;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes *
 */
public class ValidationFailedExceptionTest{

    /**
     * Ensure all constructors are still passing arguments.
     */
    @Test
    public void verify(){

        ValidationFailedException e;

        e = new ValidationFailedException("somethingelse");
        assertTrue("somethingelse".equals(e.getMessage()));

        e = new ValidationFailedException("somethingelse2", new Throwable("ThrowableMessageTest"));
        assertTrue("somethingelse2".equals(e.getMessage()));
        assertTrue("ThrowableMessageTest".equals(e.getCause().getMessage()));
    }
}