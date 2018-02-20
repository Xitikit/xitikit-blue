package org.xitikit.blue.b2c.v2dot0.authentication;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
public interface ClaimValidationService{

    boolean validateAudience(BlueWebToken token);

    boolean validateNotBefore(
        @Nonnull BlueWebToken token,
        @Nullable Long now);

    boolean validateIssuer(BlueWebToken token);

    boolean validateExpiration(BlueWebToken token, long now);
}