package org.xitikit.blue.common.errors;

import java.io.Serializable;

/**
 * An exception used to indicate that a requested resource is forbidden
 * for all users.
 *
 * @author J. Keith Hoopes
 */
public class ForbiddenException extends MessageSourceResolvableException {

    public ForbiddenException() {

        super("Forbidden");
    }

    public ForbiddenException(Throwable t) {

        super("Forbidden", t);
    }

    public ForbiddenException(Serializable... message) {

        super("Forbidden", message);
    }
}
