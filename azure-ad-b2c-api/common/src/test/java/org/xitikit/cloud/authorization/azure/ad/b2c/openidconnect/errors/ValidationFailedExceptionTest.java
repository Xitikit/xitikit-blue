package org.xitikit.blue.authorization.azure.ad.b2c.openidconnect.errors;

import org.junit.Test;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * @author J. Keith Hoopes
 *         Copyright Xitikit.org 2017
 */
public class ValidationFailedExceptionTest {

    @SuppressWarnings("ConstantConditions")
    @Test
    public void verify() {


        List<ObjectError> errors = new ArrayList<>(1);
        ObjectError error = new ObjectError("test", "test");
        errors.add(error);
        ValidationFailedException e = new ValidationFailedException(errors);

        assertTrue(e.getErrors().get(0) == error);
        assertTrue(e instanceof RuntimeException);
    }
}