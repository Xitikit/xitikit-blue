package org.xitikit.azureadb2c.common.errors;

/**
 * @author Erik Jensen
 */
public class ForbiddenException extends MessageSourceResolvableException {

    public ForbiddenException() {

        super("Forbidden");
    }

    public ForbiddenException(String message) {

        super(message);
    }

}
