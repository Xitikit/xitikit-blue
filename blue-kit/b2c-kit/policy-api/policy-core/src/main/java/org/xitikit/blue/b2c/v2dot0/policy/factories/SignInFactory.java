package org.xitikit.blue.b2c.v2dot0.policy.factories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xitikit.blue.b2c.v2dot0.policy.SignInPolicy;

import static org.xitikit.blue.b2c.v2dot0.policy.PolicyUrlUtil.*;

public final class SignInFactory{

    private static final Logger log = LoggerFactory.getLogger(SignInFactory.class);

    private final PolicyConfigurationFactory parent;

    private final SignInPolicy signIn;

    SignInFactory(final PolicyConfigurationFactory parent, final SignInPolicy signIn){

        assert parent != null;

        this.parent = parent;
        this.signIn = signIn == null ? new SignInPolicy() : signIn;
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
     * @return this {@link SignInFactory}
     */
    public SignInFactory defaultValues(){

        return name()
            .basePath()
            .redirectUrl()
            .templateUrl()
            .enable();
    }

    /**
     * Sets the basePath to {@code PolicyUrlUtil.Defaults.RESET_PASSWORD_BASE}
     *
     * @return this SignInFactory
     */
    public SignInFactory basePath(){

        return basePathOrDefault(null);
    }

    /**
     * Sets the basePath to the value of the input, or if the input is blank
     * then it will get set to {@code PolicyUrlUtil.Defaults.RESET_PASSWORD_BASE}.
     *
     * @return this SignInFactory
     */
    public SignInFactory basePathOrDefault(final String basePath){

        return basePath(checkSignInPath(basePath));
    }

    /**
     * Sets the basePath to the input without any changes.
     *
     * @param input The value to set, without any changes.
     *
     * @return this SignInFactory
     */
    public SignInFactory basePath(final String input){

        signIn.setBasePath(input);
        return this;
    }

    /**
     * Sets the target name to "b2c_1_sign_in"
     *
     * @return this SignInFactory
     */
    public SignInFactory name(){

        return nameOrDefault(null);
    }

    /**
     * Sets the name to the given input with whitespace trimmed off.
     * If the input is blank, then it sets the default value of "b2c_1_sign_in".
     *
     * @param input {@link String}
     *
     * @return this {@link SignInFactory}
     */
    public SignInFactory nameOrDefault(final String input){

        return name(
            input == null || "".equals(input.trim())
                ? "b2c_1_sign_in"
                : input);
    }

    /**
     * Sets the name to the input without any changes.
     *
     * @param input The value to set, without any changes.
     *
     * @return this SignInFactory
     */
    public SignInFactory name(final String input){

        signIn.setName(input);
        return this;
    }

    /**
     * Sets the signIn redirectUrl to an empty String.
     *
     * @return this SignInFactory
     */
    public SignInFactory redirectUrl(){

        log.warn("" +
            "The SignInPolicy redirectUrl is being set to an empty String. " +
            "This value is required for the Azure AD B2C authentication.");

        return redirectUrlOrDefault(null);
    }

    /**
     * Sets the signIn redirectUrl to the given input with whitespace trimmed off.
     * If the input is blank, then it sets the value to an empty {@link String}.
     *
     * @param input {@link String}
     *
     * @return this {@link SignInFactory}
     */
    public SignInFactory redirectUrlOrDefault(final String input){

        return redirectUrl(input == null ? "" : input);
    }

    /**
     * Sets the signIn redirectUrl to the input without any changes.
     *
     * @param input The value to set, without any changes.
     *
     * @return this SignInFactory
     */
    public SignInFactory redirectUrl(final String input){

        signIn.setRedirectUrl(input);
        return this;
    }

    /**
     * Sets the signIn 'templateUrl' to an empty String.
     *
     * @return this SignInFactory
     */
    public SignInFactory templateUrl(){

        return templateUrlOrDefault(null);
    }

    /**
     * Sets the signIn redirectUrl to the given input with whitespace trimmed off.
     * If the input is blank, then it sets the value to an empty {@link String}.
     *
     * @param input {@link String}
     *
     * @return this {@link SignInFactory}
     */
    public SignInFactory templateUrlOrDefault(final String input){

        return templateUrl(
            input == null
                ? ""
                : input.trim());
    }

    /**
     * Sets the signIn 'templateUrl' to the input without any changes.
     *
     * @param input The value to set, without any changes.
     *
     * @return this SignInFactory
     */
    public SignInFactory templateUrl(final String input){

        signIn.setTemplateUrl(input);
        return this;
    }

    /**
     * Sets the 'disabled' field to false.
     *
     * @return this {@link SignInFactory}
     */
    public SignInFactory enable(){

        signIn.setDisabled(false);
        return this;
    }

    /**
     * Sets the 'disabled' field to true.
     *
     * @return this {@link SignInFactory}
     */
    public SignInFactory disable(){

        signIn.setDisabled(true);
        return this;
    }

    /**
     * Creates a new instance of {@link SignInPolicy} which
     * contains the values set in the signIn.
     *
     * @return SignInPolicy
     */
    public SignInPolicy build(){

        return new SignInPolicy(
            signIn.getBasePath(),
            signIn.getName(),
            signIn.getRedirectUrl(),
            //No template url for this.
            signIn.isDisabled()
        );
    }
}
