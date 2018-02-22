package org.xitikit.blue.b2c.v2dot0.policy;

/**
 * This class provides some useful methods for programmatically
 * instantiating a custom policy configuration.
 * <p>
 * This factory is not thread safe.
 */
public final class PolicyConfigurationFactory{

    private final PolicyConfiguration target;

    /**
     * Force use of the instance method.
     */
    private PolicyConfigurationFactory(final PolicyConfiguration policyConfiguration){

        this.target = policyConfiguration;
    }

    public static PolicyConfigurationFactory instance(){

        return new PolicyConfigurationFactory(new PolicyConfiguration());
    }

    public static PolicyConfigurationFactory of(final PolicyConfiguration policyConfiguration){

        return new PolicyConfigurationFactory(
            policyConfiguration == null
                ? new PolicyConfiguration()
                : policyConfiguration);
    }

    /**
     * Sets all {@link String} values of every policy on the target
     * {@link PolicyConfiguration} to an empty {@link String},
     * and the "disabled" property of each to true.
     *
     * @return this instance of {@link PolicyConfigurationFactory}
     */
    public PolicyConfigurationFactory withDefaults(){

        return this
            .withPolicyBasePath(PolicyUrlUtil.Defaults.POLICY_BASE)
            .disableEditProfile()
            .disableResetPassword()
            .disableSignIn()
            .disableSignOut()
            .disableSignUp()
            .disableSignUpOrSignIn();
    }

    public PolicyConfigurationFactory withPolicyBasePath(final String basePath){

        target.setBasePath(basePath);
        return this;
    }

    /**
     * Sets all {@link String} values of the {@link SignUpOrSignInPolicy}
     * on the target {@link PolicyConfiguration} to an empty {@link String},
     * and the "disabled" property to true.
     *
     * @return this instance of {@link PolicyConfigurationFactory}
     */
    public PolicyConfigurationFactory disableSignUpOrSignIn(){

        target.setSignUpOrSignIn(
            new SignUpOrSignInPolicy(
                "", "", "", true
            )
        );
        return this;
    }

    /**
     * Sets all {@link String} values of the {@link SignUpPolicy}
     * on the target {@link PolicyConfiguration} to an empty {@link String},
     * and the "disabled" property to true.
     *
     * @return this instance of {@link PolicyConfigurationFactory}
     */
    public PolicyConfigurationFactory disableSignUp(){

        target.setSignUp(
            new SignUpPolicy(
                "", "", "", true
            )
        );

        return this;
    }

    /**
     * Sets all {@link String} values of the {@link SignOutPolicy}
     * on the target {@link PolicyConfiguration} to an empty {@link String},
     * and the "disabled" property to true.
     *
     * @return this instance of {@link PolicyConfigurationFactory}
     */
    public PolicyConfigurationFactory disableSignOut(){

        target.setSignOut(
            new SignOutPolicy(
                "", "", "", true
            )
        );
        return this;
    }

    /**
     * Sets all {@link String} values of the {@link SignInPolicy}
     * on the target {@link PolicyConfiguration} to an empty {@link String},
     * and the "disabled" property to true.
     *
     * @return this instance of {@link PolicyConfigurationFactory}
     */
    public PolicyConfigurationFactory disableSignIn(){

        target.setSignIn(
            new SignInPolicy(
                "", "", "", true
            )
        );
        return this;
    }

    /**
     * Sets all {@link String} values of the {@link ResetPasswordPolicy}
     * on the target {@link PolicyConfiguration} to an empty {@link String},
     * and the "disabled" property to true.
     *
     * @return this instance of {@link PolicyConfigurationFactory}
     */
    public PolicyConfigurationFactory disableResetPassword(){

        target.setResetPassword(
            new ResetPasswordPolicy(
                "", "", "", "", true
            )
        );
        return this;
    }

    /**
     * Sets all {@link String} values of the {@link EditProfilePolicy}
     * on the target {@link PolicyConfiguration} to an empty {@link String},
     * and the "disabled" property to true.
     *
     * @return this instance of {@link PolicyConfigurationFactory}
     */
    public PolicyConfigurationFactory disableEditProfile(){

        target.setEditProfile(
            new EditProfilePolicy(
                "", "", "", "", true
            )
        );
        return this;
    }

    /**
     * @return a new instance of {@link PolicyConfiguration}
     *     which will have all the values as have been set
     *     by this factory up to now.
     */
    public PolicyConfiguration build(){

        return new PolicyConfiguration(
            target.getBasePath(),
            target.getEditProfile() == null ? null
                : new EditProfilePolicy(
                target.getBasePath(),
                target.getEditProfile().getName(),
                target.getEditProfile().getRedirectUrl(),
                target.getEditProfile().getTemplateUrl(),
                target.getEditProfile().isDisabled()
            ),
            target.getResetPassword() == null ? null
                : new ResetPasswordPolicy(
                target.getBasePath(),
                target.getResetPassword().getName(),
                target.getResetPassword().getRedirectUrl(),
                target.getResetPassword().getTemplateUrl(),
                target.getResetPassword().isDisabled()
            ),
            target.getSignIn() == null ? null
                : new SignInPolicy(
                target.getBasePath(),
                target.getSignIn().getName(),
                target.getSignIn().getRedirectUrl(),
                target.getSignIn().isDisabled()
            ),
            target.getSignOut() == null ? null
                : new SignOutPolicy(
                target.getBasePath(),
                target.getSignOut().getName(),
                target.getSignOut().getRedirectUrl(),
                target.getSignOut().isDisabled()
            ),
            target.getSignUp() == null ? null
                : new SignUpPolicy(
                target.getSignUp().getName(),
                target.getSignUp().getRedirectUrl(),
                target.getSignUp().getTemplateUrl(),
                target.getSignUp().isDisabled()
            ),
            target.getSignUpOrSignIn() == null ? null
                : new SignUpOrSignInPolicy(
                target.getSignUpOrSignIn().getName(),
                target.getSignUpOrSignIn().getRedirectUrl(),
                target.getSignUpOrSignIn().getTemplateUrl(),
                target.getSignUpOrSignIn().isDisabled()
            )
        );
    }
}
