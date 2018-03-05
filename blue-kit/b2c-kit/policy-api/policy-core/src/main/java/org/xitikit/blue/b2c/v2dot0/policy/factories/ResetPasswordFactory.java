package org.xitikit.blue.b2c.v2dot0.policy.factories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xitikit.blue.b2c.v2dot0.policy.ResetPasswordPolicy;

import static org.xitikit.blue.b2c.v2dot0.policy.PolicyUrlUtil.*;

public final class ResetPasswordFactory{

    private static final Logger log = LoggerFactory.getLogger(ResetPasswordFactory.class);

    private final PolicyConfigurationFactory parent;

    private final ResetPasswordPolicy resetPassword;

    ResetPasswordFactory(final PolicyConfigurationFactory parent, final ResetPasswordPolicy resetPassword){

        assert parent != null;

        this.parent = parent;
        this.resetPassword = resetPassword == null ? new ResetPasswordPolicy() : resetPassword;
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
     * @return this {@link ResetPasswordFactory}
     */
    public ResetPasswordFactory defaultValues(){

        return name()
            .basePath()
            .redirectUrl()
            .templateUrl()
            .enable();
    }

    /**
     * Sets the basePath to {@code PolicyUrlUtil.Defaults.RESET_PASSWORD_BASE}
     *
     * @return this ResetPasswordFactory
     */
    public ResetPasswordFactory basePath(){

        return basePathOrDefault(null);
    }

    /**
     * Sets the basePath to the value of the input, or if the input is blank
     * then it will get set to {@code PolicyUrlUtil.Defaults.RESET_PASSWORD_BASE}.
     *
     * @return this ResetPasswordFactory
     */
    public ResetPasswordFactory basePathOrDefault(final String basePath){

        return basePath(checkResetPasswordPath(basePath));
    }

    /**
     * Sets the basePath to the input without any changes.
     *
     * @param input The value to set, without any changes.
     *
     * @return this ResetPasswordFactory
     */
    public ResetPasswordFactory basePath(final String input){

        resetPassword.setBasePath(input);
        return this;
    }

    /**
     * Sets the target name to "b2c_1_reset_password"
     *
     * @return this ResetPasswordFactory
     */
    public ResetPasswordFactory name(){

        return nameOrDefault(null);
    }

    /**
     * Sets the name to the given input with whitespace trimmed off.
     * If the input is blank, then it sets the default value of "b2c_1_reset_password".
     *
     * @param input {@link String}
     *
     * @return this {@link ResetPasswordFactory}
     */
    public ResetPasswordFactory nameOrDefault(final String input){

        return name(
            input == null || "".equals(input.trim())
                ? "b2c_1_reset_password"
                : input);
    }

    /**
     * Sets the name to the input without any changes.
     *
     * @param input The value to set, without any changes.
     *
     * @return this ResetPasswordFactory
     */
    public ResetPasswordFactory name(final String input){

        resetPassword.setName(input);
        return this;
    }

    /**
     * Sets the resetPassword redirectUrl to an empty String.
     *
     * @return this ResetPasswordFactory
     */
    public ResetPasswordFactory redirectUrl(){

        log.warn("" +
            "The ResetPasswordPolicy redirectUrl is being set to an empty String. " +
            "This value is required for the Azure AD B2C authentication.");

        return redirectUrlOrDefault(null);
    }

    /**
     * Sets the resetPassword redirectUrl to the given input with whitespace trimmed off.
     * If the input is blank, then it sets the value to an empty {@link String}.
     *
     * @param input {@link String}
     *
     * @return this {@link ResetPasswordFactory}
     */
    public ResetPasswordFactory redirectUrlOrDefault(final String input){

        return redirectUrl(input == null ? "" : input);
    }

    /**
     * Sets the resetPassword redirectUrl to the input without any changes.
     *
     * @param input The value to set, without any changes.
     *
     * @return this ResetPasswordFactory
     */
    public ResetPasswordFactory redirectUrl(final String input){

        resetPassword.setRedirectUrl(input);
        return this;
    }

    /**
     * Sets the resetPassword 'templateUrl' to an empty String.
     *
     * @return this ResetPasswordFactory
     */
    public ResetPasswordFactory templateUrl(){

        return templateUrlOrDefault(null);
    }

    /**
     * Sets the resetPassword redirectUrl to the given input with whitespace trimmed off.
     * If the input is blank, then it sets the value to an empty {@link String}.
     *
     * @param input {@link String}
     *
     * @return this {@link ResetPasswordFactory}
     */
    public ResetPasswordFactory templateUrlOrDefault(final String input){

        return templateUrl(
            input == null
                ? ""
                : input.trim());
    }

    /**
     * Sets the resetPassword 'templateUrl' to the input without any changes.
     *
     * @param input The value to set, without any changes.
     *
     * @return this ResetPasswordFactory
     */
    public ResetPasswordFactory templateUrl(final String input){

        resetPassword.setTemplateUrl(input);
        return this;
    }

    /**
     * Sets the 'disabled' field to false.
     *
     * @return this {@link ResetPasswordFactory}
     */
    public ResetPasswordFactory enable(){

        resetPassword.setDisabled(false);
        return this;
    }

    /**
     * Sets the 'disabled' field to true.
     *
     * @return this {@link ResetPasswordFactory}
     */
    public ResetPasswordFactory disable(){

        resetPassword.setDisabled(true);
        return this;
    }

    /**
     * Creates a new instance of {@link ResetPasswordPolicy} which
     * contains the values set in the resetPassword.
     *
     * @return ResetPasswordPolicy
     */
    public ResetPasswordPolicy build(){

        return new ResetPasswordPolicy(
            resetPassword.getBasePath(),
            resetPassword.getName(),
            resetPassword.getRedirectUrl(),
            resetPassword.getTemplateUrl(),
            resetPassword.isDisabled()
        );
    }
}
