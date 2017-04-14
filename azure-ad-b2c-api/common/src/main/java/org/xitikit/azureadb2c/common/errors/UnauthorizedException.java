package org.xitikit.azureadb2c.common.errors;

import java.io.Serializable;

/**
 * @author J. Keith Hoopes
 */
public class UnauthorizedException extends MessageSourceResolvableException {

    public UnauthorizedException() {

        super("Unauthorized");
    }

    public UnauthorizedException(Serializable... args) {

        super("Unauthorized", args);
    }

    public UnauthorizedException(Throwable t) {

        super("Unauthorized", t);
    }
}
