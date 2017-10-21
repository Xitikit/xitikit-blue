package org.xitikit.blue.kit.authentication.v2dot0;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.xitikit.blue.api.b2c.v2dot0.configuration.AuthenticationProperties;
import org.xitikit.blue.api.b2c.v2dot0.configuration.B2CProperties;
import org.xitikit.blue.kit.authentication.v2dot0.interfaces.ClaimValidationService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static org.xitikit.blue.kit.authentication.v2dot0.TimeComparison.*;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
@SuppressWarnings("WeakerAccess")
@Service
public class SimpleClaimValidationService implements ClaimValidationService{

    private final B2CProperties b2CProperties;

    private final AuthenticationProperties authenticationProperties;

    @Autowired
    public SimpleClaimValidationService(
      final B2CProperties b2CProperties,
      final AuthenticationProperties authenticationProperties){

        Assert.notNull(b2CProperties, "Missing required parameter 'b2CProperties' (ClaimValidationService::new)");
        Assert.notNull(b2CProperties, "Missing required parameter 'authenticationProperties' (ClaimValidationService::new)");

        this.b2CProperties = b2CProperties;
        this.authenticationProperties = authenticationProperties;
    }

    @Override
    public boolean validateAudience(final BlueWebToken token){

        return b2CProperties
          .getAppId()
          .equals(token.getAudience());
    }

    @Override
    public boolean validateNotBefore(
      @Nonnull final BlueWebToken token,
      @Nullable final Long now){

        Assert.notNull(token, "Missing required parameter 'token' (ClaimValidationService::validateNotBefore)");

        // While unlikely, it's ok to be equal.
        return comparisonOf(
          token.getNotBefore(),
          paddedNow(now)
        ).isLessOrEqual();
    }

    private Long paddedNow(final Long now){

        return now == null ? null :
          now + authenticationProperties
            .getNotBefore()
            .getPaddingInMilliseconds();
    }

    @Override
    public boolean validateIssuer(final BlueWebToken token){

        return VerificationUtil.validateIssuer(token);
    }

    @Override
    public boolean validateExpiration(final BlueWebToken token, final long now){

        return VerificationUtil.validateExpiration(token, now);
    }
}
