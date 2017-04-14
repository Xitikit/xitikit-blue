package org.xitikit.blue.common.errors;

import java.io.Serializable;

/**
 * @author J. Keith Hoopes
 */
public class NotFoundException extends MessageSourceResolvableException {

    public NotFoundException() {

        super("NotFound");
    }

    public NotFoundException(Serializable... args) {

        super("NotFound", args);
    }

    public NotFoundException(Throwable t) {

        super("NotFound", t);
    }
}
