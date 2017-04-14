package org.xitikit.azureadb2c.common.errors;

/**
 * @author Erik R. Jensen
 */
public class UnauthorizedException extends MessageSourceResolvableException {

    public UnauthorizedException() {

        super("Unauthorized");
    }
}
