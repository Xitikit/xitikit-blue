package org.xitikit.blue.b2c.v2dot0.policy.factories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xitikit.blue.b2c.v2dot0.policy.SignUpOrSignInPolicy;

import static org.xitikit.blue.b2c.v2dot0.policy.PolicyUrlUtil.*;

public final class SignUpOrSignInFactory{

    private static final Logger log = LoggerFactory.getLogger(SignUpOrSignInFactory.class);

    private final PolicyConfigurationFactory parent;

    private final SignUpOrSignInPolicy signUpOrSignIn;

    SignUpOrSignInFactory(final PolicyConfigurationFactory parent, final SignUpOrSignInPolicy signUpOrSignIn){

        assert parent != null;

        this.parent = parent;
        this.signUpOrSignIn = signUpOrSignIn == null ? new SignUpOrSignInPolicy() : signUpOrSignIn;
    }

    /**
     * @return the parent {@link PolicyConfigurationFactory} to continue configuration of other policies.
     */
    public PolicyConfigurationFactory and(){

        return parent;
    }

    /**
     * Sets default values for all fields on the target.
     * <p>
     * Note: the default values for 'redirectUrl' and 'templateUrl'
     * fields are an empty {@link String}.
     *
     * @return this {@link SignUpOrSignInFactory}
     */
    public SignUpOrSignInFactory defaultValues(){

        return name()
            .basePath()
            .redirectUrl()
            .templateUrl()
            .enable();
    }

    /**
     * Sets the basePath to {@code PolicyUrlUtil.Defaults.RESET_PASSWORD_BASE}
     *
     * @return this SignUpOrSignInFactory
     */
    public SignUpOrSignInFactory basePath(){

        return basePathOrDefault(null);
    }

    /**
     * Sets the basePath to the value of the input, or if the input is blank
     * then it will get set to {@code PolicyUrlUtil.Defaults.SIGN_UP_OR_SIGN_IN_BASE}.
     *
     * @return this SignUpOrSignInFactory
     */
    public SignUpOrSignInFactory basePathOrDefault(final String basePath){

        return basePath(checkSignUpOrSignInPath(basePath));
    }

    /**
     * Sets the basePath to the input without any changes.
     *
     * @param input The value to set, without any changes.
     *
     * @return this SignUpOrSignInFactory
     */
    public SignUpOrSignInFactory basePath(final String input){

        signUpOrSignIn.setBasePath(input);
        return this;
    }

    /**
     * Sets the target name to "b2c_1_sign_up_or_sign_in"
     *
     * @return this SignUpOrSignInFactory
     */
    public SignUpOrSignInFactory name(){

        return nameOrDefault(null);
    }

    /**
     * Sets the name to the given input with whitespace trimmed off.
     * If the input is blank, then it sets the default value of "b2c_1_sign_up_or_sign_in".
     *
     * @param input {@link String}
     *
     * @return this {@link SignUpOrSignInFactory}
     */
    public SignUpOrSignInFactory nameOrDefault(final String input){

        return name(
            input == null || "".equals(input.trim())
                ? "b2c_1_sign_up_or_sign_in"
                : input);
    }

    /**
     * Sets the name to the input without any changes.
     *
     * @param input The value to set, without any changes.
     *
     * @return this SignUpOrSignInFactory
     */
    public SignUpOrSignInFactory name(final String input){

        signUpOrSignIn.setName(input);
        return this;
    }

    /**
     * Sets the signUpOrSignIn redirectUrl to an empty String.
     *
     * @return this SignUpOrSignInFactory
     */
    public SignUpOrSignInFactory redirectUrl(){

        log.warn("" +
            "The SignUpOrSignInPolicy redirectUrl is being set to an empty String. " +
            "This value is required for the Azure AD B2C authentication.");

        return redirectUrlOrDefault(null);
    }

    /**
     * Sets the signUpOrSignIn redirectUrl to the given input with whitespace trimmed off.
     * If the input is blank, then it sets the value to an empty {@link String}.
     *
     * @param input {@link String}
     *
     * @return this {@link SignUpOrSignInFactory}
     */
    public SignUpOrSignInFactory redirectUrlOrDefault(final String input){

        return redirectUrl(input == null ? "" : input);
    }

    /**
     * Sets the signUpOrSignIn redirectUrl to the input without any changes.
     *
     * @param input The value to set, without any changes.
     *
     * @return this SignUpOrSignInFactory
     */
    public SignUpOrSignInFactory redirectUrl(final String input){

        signUpOrSignIn.setRedirectUrl(input);
        return this;
    }

    /**
     * Sets the signUpOrSignIn 'templateUrl' to an empty String.
     *
     * @return this SignUpOrSignInFactory
     */
    public SignUpOrSignInFactory templateUrl(){

        return templateUrlOrDefault(null);
    }

    /**
     * Sets the signUpOrSignIn redirectUrl to the given input with whitespace trimmed off.
     * If the input is blank, then it sets the value to an empty {@link String}.
     *
     * @param input {@link String}
     *
     * @return this {@link SignUpOrSignInFactory}
     */
    public SignUpOrSignInFactory templateUrlOrDefault(final String input){

        return templateUrl(
            input == null
                ? ""
                : input.trim());
    }

    /**
     * Sets the signUpOrSignIn 'templateUrl' to the input without any changes.
     *
     * @param input The value to set, without any changes.
     *
     * @return this SignUpOrSignInFactory
     */
    public SignUpOrSignInFactory templateUrl(final String input){

        signUpOrSignIn.setTemplateUrl(input);
        return this;
    }

    /**
     * Sets the 'disabled' field to false.
     *
     * @return this {@link SignUpOrSignInFactory}
     */
    public SignUpOrSignInFactory enable(){

        signUpOrSignIn.setDisabled(false);
        return this;
    }

    /**
     * Sets the 'disabled' field to true.
     *
     * @return this {@link SignUpOrSignInFactory}
     */
    public SignUpOrSignInFactory disable(){

        signUpOrSignIn.setDisabled(true);
        return this;
    }

    /**
     * Creates a new instance of {@link SignUpOrSignInPolicy} which
     * contains the values set in the signUpOrSignIn.
     *
     * @return SignUpOrSignInPolicy
     */
    public SignUpOrSignInPolicy build(){

        return new SignUpOrSignInPolicy(
            signUpOrSignIn.getBasePath(),
            signUpOrSignIn.getName(),
            signUpOrSignIn.getRedirectUrl(),
            signUpOrSignIn.getTemplateUrl(),
            signUpOrSignIn.isDisabled()
        );
    }
}
