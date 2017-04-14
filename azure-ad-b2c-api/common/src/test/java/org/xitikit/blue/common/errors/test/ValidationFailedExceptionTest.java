package org.xitikit.blue.common.errors.test;

import org.junit.Test;
import org.springframework.validation.ObjectError;
import org.xitikit.blue.common.errors.ValidationFailedException;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes *
 */
public class ValidationFailedExceptionTest {

    /**
     * Ensure all constructors are still passing arguments.
     * <p>
     * I forgot my meds, but this exception is not meant for rest controller error handlers.
     */
    @Test
    public void verify() {


        List<ObjectError> errors = new ArrayList<>(1);
        ObjectError error = new ObjectError("test", "test");
        errors.add(error);

        ValidationFailedException e;

        e = new ValidationFailedException(errors);
        assertTrue(e.getErrors().get(0) == error);

        e = new ValidationFailedException("somethingelse", errors);
        assertTrue(e.getErrors().get(0) == error);
        assertTrue("somethingelse".equals(e.getMessage()));

        e = new ValidationFailedException("somethingelse2", new Throwable("ThrowableMessageTest"), errors);
        assertTrue(e.getErrors().get(0) == error);
        assertTrue("somethingelse2".equals(e.getMessage()));
        assertTrue("ThrowableMessageTest".equals(e.getCause().getMessage()));
    }
}