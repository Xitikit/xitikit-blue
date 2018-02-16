package org.xitikit.blue.b2c.kit.v2dot0.authentication.interfaces;

import org.xitikit.blue.b2c.kit.v2dot0.authentication.BlueWebToken;

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
