package org.xitikit.blue.authorization.azure.ad.b2c.openidconnect.errors;

/**
 * @author Erik R. Jensen
 */
public class UnauthorizedException extends MessageSourceResolvableException {

    public UnauthorizedException() {

        super("Unauthorized");
    }
}
