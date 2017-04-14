package org.xitikit.blue.common.errors;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * @author J. Keith Hoopes
 */
@Getter
public class ValidationFailedException extends RuntimeException {

    private final List<ObjectError> errors;

    public ValidationFailedException(List<ObjectError> errors) {

        super("Unauthorized");
        this.errors = errors;
    }

    public ValidationFailedException(String message, List<ObjectError> errors) {

        super(message);
        this.errors = errors;
    }

    public ValidationFailedException(String message, Throwable t, List<ObjectError> errors) {

        super(message, t);
        this.errors = errors;
    }
}
