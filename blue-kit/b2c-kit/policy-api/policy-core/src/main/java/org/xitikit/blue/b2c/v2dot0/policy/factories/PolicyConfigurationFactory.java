package org.xitikit.blue.b2c.v2dot0.policy.factories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xitikit.blue.b2c.v2dot0.policy.*;

import javax.annotation.Nonnull;

/**
 * This class provides some useful methods for programmatically
 * instantiating a custom policy configuration.
 * <p>
 * This factory is not thread safe.
 */
public final class PolicyConfigurationFactory{

    private static final Logger log = LoggerFactory.getLogger(PolicyConfigurationFactory.class);

    private final EditProfileFactory editProfile;

    private final ResetPasswordFactory resetPassword;

    private final SignInFactory signIn;

    private final SignOutFactory signOut;

    private final SignUpFactory signUp;

    private final SignUpOrSignInFactory signUpOrSignIn;

    /**
     * Force use of the instance method.
     */
    private PolicyConfigurationFactory(final PolicyConfiguration policyConfiguration){

        assert policyConfiguration != null;

        if(log.isDebugEnabled()){
            log.debug("Creating PolicyConfigurationFactory for: " + policyConfiguration);
        }
        this.editProfile = new EditProfileFactory(this, policyConfiguration.getEditProfile());
        this.resetPassword = new ResetPasswordFactory(this, policyConfiguration.getResetPassword());
        this.signIn = new SignInFactory(this, policyConfiguration.getSignIn());
        this.signOut = new SignOutFactory(this, policyConfiguration.getSignOut());
        this.signUp = new SignUpFactory(this, policyConfiguration.getSignUp());
        this.signUpOrSignIn = new SignUpOrSignInFactory(this, policyConfiguration.getSignUpOrSignIn());
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
     * @return a reference to an {@link EditProfileFactory} to configure the {@link EditProfilePolicy}
     */
    @Nonnull
    public EditProfileFactory editProfile(){

        return editProfile;
    }

    /**
     * @return a reference to a {@link ResetPasswordFactory} to configure the {@link ResetPasswordPolicy}
     */
    @Nonnull
    public ResetPasswordFactory resetPassword(){

        return resetPassword;
    }

    /**
     * @return a reference to a {@link SignInFactory} to configure the {@link SignInPolicy}
     */
    @Nonnull
    public SignInFactory signIn(){

        return signIn;
    }

    /**
     * @return a reference to a {@link SignOutFactory} to configure the {@link SignOutPolicy}
     */
    @Nonnull
    public SignOutFactory signOut(){

        return signOut;
    }

    /**
     * @return a reference to a {@link SignUpFactory} to configure the {@link SignUpPolicy}
     */
    @Nonnull
    public SignUpFactory signUp(){

        return signUp;
    }

    /**
     * @return a reference to a {@link SignUpOrSignInFactory} to configure the {@link SignUpOrSignInPolicy}
     */
    @Nonnull
    public SignUpOrSignInFactory signUpOrSignIn(){

        return signUpOrSignIn;
    }

    /**
     * Sets all {@link String} values of every policy on the target
     * {@link PolicyConfiguration} to an empty {@link String},
     * and the "disabled" property of each to true.
     *
     * @return this instance of {@link PolicyConfigurationFactory}
     */
    public PolicyConfigurationFactory disableAll(){

        return editProfile.disable().and()
            .resetPassword.disable().and()
            .signIn.disable().and()
            .signOut.disable().and()
            .signUp.disable().and()
            .signUpOrSignIn.disable().and();
    }

    /**
     * Sets default values for all fields on the target.
     * <p>
     * Note: the default values for 'redirectUrl' and 'templateUrl'
     * fields are an empty {@link String}.
     *
     * @return this {@link PolicyConfigurationFactory}
     */
    public PolicyConfigurationFactory defaultValues(){

        return editProfile.defaultValues().and()
            .resetPassword.defaultValues().and()
            .signIn.defaultValues().and()
            .signOut.defaultValues().and()
            .signUp.defaultValues().and()
            .signUpOrSignIn.defaultValues().and();
    }

    /**
     * @return a new instance of {@link PolicyConfiguration}
     *     which will have all the values as have been set
     *     by this factory up to now.
     */
    public PolicyConfiguration build(){

        return new PolicyConfiguration(
            editProfile.build(),
            resetPassword.build(),
            signIn.build(),
            signOut.build(),
            signUp.build(),
            signUpOrSignIn.build()
        );
    }

}
