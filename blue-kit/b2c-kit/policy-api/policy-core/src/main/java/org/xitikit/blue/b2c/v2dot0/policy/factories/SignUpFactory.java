package org.xitikit.blue.b2c.v2dot0.policy.factories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xitikit.blue.b2c.v2dot0.policy.SignUpPolicy;

import static org.xitikit.blue.b2c.v2dot0.policy.PolicyUrlUtil.*;

public final class SignUpFactory{

    private static final Logger log = LoggerFactory.getLogger(SignUpFactory.class);

    private final PolicyConfigurationFactory parent;

    private final SignUpPolicy signUp;

    SignUpFactory(final PolicyConfigurationFactory parent, final SignUpPolicy signUp){

        assert parent != null;

        this.parent = parent;
        this.signUp = signUp == null ? new SignUpPolicy() : signUp;
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
     * @return this {@link SignUpFactory}
     */
    public SignUpFactory defaultValues(){

        return name()
            .basePath()
            .redirectUrl()
            .templateUrl()
            .enable();
    }

    /**
     * Sets the basePath to {@code PolicyUrlUtil.Defaults.RESET_PASSWORD_BASE}
     *
     * @return this SignUpFactory
     */
    public SignUpFactory basePath(){

        return basePathOrDefault(null);
    }

    /**
     * Sets the basePath to the value of the input, or if the input is blank
     * then it will get set to {@code PolicyUrlUtil.Defaults.RESET_PASSWORD_BASE}.
     *
     * @return this SignUpFactory
     */
    public SignUpFactory basePathOrDefault(final String basePath){

        return basePath(checkSignUpPath(basePath));
    }

    /**
     * Sets the basePath to the input without any changes.
     *
     * @param input The value to set, without any changes.
     *
     * @return this SignUpFactory
     */
    public SignUpFactory basePath(final String input){

        signUp.setBasePath(input);
        return this;
    }

    /**
     * Sets the target name to "b2c_1_sign_up"
     *
     * @return this SignUpFactory
     */
    public SignUpFactory name(){

        return nameOrDefault(null);
    }

    /**
     * Sets the name to the given input with whitespace trimmed off.
     * If the input is blank, then it sets the default value of "b2c_1_sign_up".
     *
     * @param input {@link String}
     *
     * @return this {@link SignUpFactory}
     */
    public SignUpFactory nameOrDefault(final String input){

        return name(
            input == null || "".equals(input.trim())
                ? "b2c_1_sign_up"
                : input);
    }

    /**
     * Sets the name to the input without any changes.
     *
     * @param input The value to set, without any changes.
     *
     * @return this SignUpFactory
     */
    public SignUpFactory name(final String input){

        signUp.setName(input);
        return this;
    }

    /**
     * Sets the signUp redirectUrl to an empty String.
     *
     * @return this SignUpFactory
     */
    public SignUpFactory redirectUrl(){

        log.warn("" +
            "The SignUpPolicy redirectUrl is being set to an empty String. " +
            "This value is required for the Azure AD B2C authentication.");

        return redirectUrlOrDefault(null);
    }

    /**
     * Sets the signUp redirectUrl to the given input with whitespace trimmed off.
     * If the input is blank, then it sets the value to an empty {@link String}.
     *
     * @param input {@link String}
     *
     * @return this {@link SignUpFactory}
     */
    public SignUpFactory redirectUrlOrDefault(final String input){

        return redirectUrl(input == null ? "" : input);
    }

    /**
     * Sets the signUp redirectUrl to the input without any changes.
     *
     * @param input The value to set, without any changes.
     *
     * @return this SignUpFactory
     */
    public SignUpFactory redirectUrl(final String input){

        signUp.setRedirectUrl(input);
        return this;
    }

    /**
     * Sets the signUp 'templateUrl' to an empty String.
     *
     * @return this SignUpFactory
     */
    public SignUpFactory templateUrl(){

        return templateUrlOrDefault(null);
    }

    /**
     * Sets the signUp redirectUrl to the given input with whitespace trimmed off.
     * If the input is blank, then it sets the value to an empty {@link String}.
     *
     * @param input {@link String}
     *
     * @return this {@link SignUpFactory}
     */
    public SignUpFactory templateUrlOrDefault(final String input){

        return templateUrl(
            input == null
                ? ""
                : input.trim());
    }

    /**
     * Sets the signUp 'templateUrl' to the input without any changes.
     *
     * @param input The value to set, without any changes.
     *
     * @return this SignUpFactory
     */
    public SignUpFactory templateUrl(final String input){

        signUp.setTemplateUrl(input);
        return this;
    }

    /**
     * Sets the 'disabled' field to false.
     *
     * @return this {@link SignUpFactory}
     */
    public SignUpFactory enable(){

        signUp.setDisabled(false);
        return this;
    }

    /**
     * Sets the 'disabled' field to true.
     *
     * @return this {@link SignUpFactory}
     */
    public SignUpFactory disable(){

        signUp.setDisabled(true);
        return this;
    }

    /**
     * Creates a new instance of {@link SignUpPolicy} which
     * contains the values set in the signUp.
     *
     * @return SignUpPolicy
     */
    public SignUpPolicy build(){

        return new SignUpPolicy(
            signUp.getBasePath(),
            signUp.getName(),
            signUp.getRedirectUrl(),
            signUp.getTemplateUrl(),
            signUp.isDisabled()
        );
    }
}
