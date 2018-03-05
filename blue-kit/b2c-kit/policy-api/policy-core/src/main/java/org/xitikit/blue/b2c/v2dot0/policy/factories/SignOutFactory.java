package org.xitikit.blue.b2c.v2dot0.policy.factories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xitikit.blue.b2c.v2dot0.policy.SignOutPolicy;

import static org.xitikit.blue.b2c.v2dot0.policy.PolicyUrlUtil.*;

public final class SignOutFactory{

    private static final Logger log = LoggerFactory.getLogger(SignOutFactory.class);

    private final PolicyConfigurationFactory parent;

    private final SignOutPolicy signOut;

    SignOutFactory(final PolicyConfigurationFactory parent, final SignOutPolicy signOut){

        assert parent != null;

        this.parent = parent;
        this.signOut = signOut == null ? new SignOutPolicy() : signOut;
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
     * @return this {@link SignOutFactory}
     */
    public SignOutFactory defaultValues(){

        return name()
            .basePath()
            .redirectUrl()
            .templateUrl()
            .enable();
    }

    /**
     * Sets the basePath to {@code PolicyUrlUtil.Defaults.RESET_PASSWORD_BASE}
     *
     * @return this SignOutFactory
     */
    public SignOutFactory basePath(){

        return basePathOrDefault(null);
    }

    /**
     * Sets the basePath to the value of the input, or if the input is blank
     * then it will get set to {@code PolicyUrlUtil.Defaults.RESET_PASSWORD_BASE}.
     *
     * @return this SignOutFactory
     */
    public SignOutFactory basePathOrDefault(final String basePath){

        return basePath(checkSignOutPath(basePath));
    }

    /**
     * Sets the basePath to the input without any changes.
     *
     * @param input The value to set, without any changes.
     *
     * @return this SignOutFactory
     */
    public SignOutFactory basePath(final String input){

        signOut.setBasePath(input);
        return this;
    }

    /**
     * Sets the target name to "b2c_1_sign_out"
     *
     * @return this SignOutFactory
     */
    public SignOutFactory name(){

        return nameOrDefault(null);
    }

    /**
     * Sets the name to the given input with whitespace trimmed off.
     * If the input is blank, then it sets the default value of "b2c_1_sign_out".
     *
     * @param input {@link String}
     *
     * @return this {@link SignOutFactory}
     */
    public SignOutFactory nameOrDefault(final String input){

        return name(
            input == null || "".equals(input.trim())
                ? "b2c_1_sign_out"
                : input);
    }

    /**
     * Sets the name to the input without any changes.
     *
     * @param input The value to set, without any changes.
     *
     * @return this SignOutFactory
     */
    public SignOutFactory name(final String input){

        signOut.setName(input);
        return this;
    }

    /**
     * Sets the signOut redirectUrl to an empty String.
     *
     * @return this SignOutFactory
     */
    public SignOutFactory redirectUrl(){

        log.warn("" +
            "The SignOutPolicy redirectUrl is being set to an empty String. " +
            "This value is required for the Azure AD B2C authentication.");

        return redirectUrlOrDefault(null);
    }

    /**
     * Sets the signOut redirectUrl to the given input with whitespace trimmed off.
     * If the input is blank, then it sets the value to an empty {@link String}.
     *
     * @param input {@link String}
     *
     * @return this {@link SignOutFactory}
     */
    public SignOutFactory redirectUrlOrDefault(final String input){

        return redirectUrl(input == null ? "" : input);
    }

    /**
     * Sets the signOut redirectUrl to the input without any changes.
     *
     * @param input The value to set, without any changes.
     *
     * @return this SignOutFactory
     */
    public SignOutFactory redirectUrl(final String input){

        signOut.setRedirectUrl(input);
        return this;
    }

    /**
     * Sets the signOut 'templateUrl' to an empty String.
     *
     * @return this SignOutFactory
     */
    public SignOutFactory templateUrl(){

        return templateUrlOrDefault(null);
    }

    /**
     * Sets the signOut redirectUrl to the given input with whitespace trimmed off.
     * If the input is blank, then it sets the value to an empty {@link String}.
     *
     * @param input {@link String}
     *
     * @return this {@link SignOutFactory}
     */
    public SignOutFactory templateUrlOrDefault(final String input){

        return templateUrl(
            input == null
                ? ""
                : input.trim());
    }

    /**
     * Sets the signOut 'templateUrl' to the input without any changes.
     *
     * @param input The value to set, without any changes.
     *
     * @return this SignOutFactory
     */
    public SignOutFactory templateUrl(final String input){

        signOut.setTemplateUrl(input);
        return this;
    }

    /**
     * Sets the 'disabled' field to false.
     *
     * @return this {@link SignOutFactory}
     */
    public SignOutFactory enable(){

        signOut.setDisabled(false);
        return this;
    }

    /**
     * Sets the 'disabled' field to true.
     *
     * @return this {@link SignOutFactory}
     */
    public SignOutFactory disable(){

        signOut.setDisabled(true);
        return this;
    }

    /**
     * Creates a new instance of {@link SignOutPolicy} which
     * contains the values set in the signOut.
     *
     * @return SignOutPolicy
     */
    public SignOutPolicy build(){

        return new SignOutPolicy(
            signOut.getBasePath(),
            signOut.getName(),
            signOut.getRedirectUrl(),
            //No template url for this.
            signOut.isDisabled()
        );
    }
}
