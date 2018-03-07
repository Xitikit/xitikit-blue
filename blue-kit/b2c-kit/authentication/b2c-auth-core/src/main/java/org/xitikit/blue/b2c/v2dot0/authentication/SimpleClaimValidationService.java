package org.xitikit.blue.b2c.v2dot0.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xitikit.blue.common.properties.B2CProperties;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static org.xitikit.blue.b2c.v2dot0.authentication.TimeComparison.*;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
public class SimpleClaimValidationService implements ClaimValidationService{

    private static final Logger log = LoggerFactory.getLogger(SimpleClaimValidationService.class);

    private final B2CProperties b2CProperties;

    private final AuthenticationProperties authenticationProperties;

    public SimpleClaimValidationService(
        @Nonnull final B2CProperties b2CProperties,
        @Nonnull final AuthenticationProperties authenticationProperties){

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

        return comparisonOf(
            token.getNotBefore(),
            paddedNow(now)
        ).isLessOrEqual();// While unlikely, it's ok to be equal.
    }

    @Override
    public boolean validateIssuer(@Nonnull final BlueWebToken token){

        return VerificationUtil.validateIssuer(token);
    }

    @Override
    public boolean validateExpiration(@Nonnull final BlueWebToken token, final long now){

        return VerificationUtil.validateExpiration(token, now);
    }

    private Long paddedNow(final Long now){

        return now == null ? null :
            now + authenticationProperties
                .getNotBefore()
                .getPaddingInMilliseconds();
    }
}
