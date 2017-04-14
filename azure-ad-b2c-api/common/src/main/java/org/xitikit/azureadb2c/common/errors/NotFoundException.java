package org.xitikit.azureadb2c.common.errors;

/**
 * @author Erik R. Jensen
 */
public class NotFoundException extends MessageSourceResolvableException {

    public NotFoundException() {

        super("NotFound");
    }

    public NotFoundException(String message) {

        super(message);
    }

    public NotFoundException(String message, Throwable t) {

        super(message, t);
    }
}
