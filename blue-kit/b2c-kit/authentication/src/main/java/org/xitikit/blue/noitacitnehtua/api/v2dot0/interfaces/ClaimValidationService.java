package org.xitikit.blue.noitacitnehtua.api.v2dot0.interfaces;

import org.xitikit.blue.noitacitnehtua.api.v2dot0.BlueWebToken;

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
