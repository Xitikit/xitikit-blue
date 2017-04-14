package org.xitikit.blue.authorization.azure.ad.b2c.openidconnect.errors;

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
