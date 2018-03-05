package org.xitikit.blue.b2c.v2dot0.policy.factories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xitikit.blue.b2c.v2dot0.policy.EditProfilePolicy;

import static org.xitikit.blue.b2c.v2dot0.policy.PolicyUrlUtil.*;

public final class EditProfileFactory{

    private static final Logger log = LoggerFactory.getLogger(EditProfileFactory.class);

    private final PolicyConfigurationFactory parent;

    private final EditProfilePolicy editProfile;

    EditProfileFactory(final PolicyConfigurationFactory parent, final EditProfilePolicy editProfile){

        assert parent != null;

        this.parent = parent;
        this.editProfile = editProfile == null ? new EditProfilePolicy() : editProfile;
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
     * @return this {@link EditProfileFactory}
     */
    public EditProfileFactory defaultValues(){

        return name()
            .basePath()
            .redirectUrl()
            .templateUrl()
            .enable();
    }

    /**
     * Sets the basePath to {@code PolicyUrlUtil.Defaults.EDIT_PROFILE_BASE}
     *
     * @return this EditProfileFactory
     */
    public EditProfileFactory basePath(){

        return basePathOrDefault(null);
    }

    /**
     * Sets the basePath to the value of the input, or if the input is blank
     * then it will get set to {@code PolicyUrlUtil.Defaults.EDIT_PROFILE_BASE}.
     *
     * @return this EditProfileFactory
     */
    public EditProfileFactory basePathOrDefault(final String basePath){

        return basePath(checkEditProfilePath(basePath));
    }

    /**
     * Sets the basePath to the input without any changes.
     *
     * @param input The value to set, without any changes.
     *
     * @return this EditProfileFactory
     */
    public EditProfileFactory basePath(final String input){

        editProfile.setBasePath(input);
        return this;
    }

    /**
     * Sets the target name to "b2c_1_edit_profile"
     *
     * @return this EditProfileFactory
     */
    public EditProfileFactory name(){

        return nameOrDefault(null);
    }

    /**
     * Sets the name to the given input with whitespace trimmed off.
     * If the input is blank, then it sets the default value of "b2c_1_edit_profile".
     *
     * @param input {@link String}
     *
     * @return this {@link EditProfileFactory}
     */
    public EditProfileFactory nameOrDefault(final String input){

        return name(
            input == null || "".equals(input.trim())
                ? "b2c_1_edit_profile"
                : input);
    }

    /**
     * Sets the name to the input without any changes.
     *
     * @param input The value to set, without any changes.
     *
     * @return this EditProfileFactory
     */
    public EditProfileFactory name(final String input){

        editProfile.setName(input);
        return this;
    }

    /**
     * Sets the editProfile redirectUrl to an empty String.
     *
     * @return this EditProfileFactory
     */
    public EditProfileFactory redirectUrl(){

        log.warn("" +
            "The EditProfilePolicy redirectUrl is being set to an empty String. " +
            "This value is required for the Azure AD B2C authentication.");

        return redirectUrlOrDefault(null);
    }

    /**
     * Sets the editProfile redirectUrl to the given input with whitespace trimmed off.
     * If the input is blank, then it sets the value to an empty {@link String}.
     *
     * @param input {@link String}
     *
     * @return this {@link EditProfileFactory}
     */
    public EditProfileFactory redirectUrlOrDefault(final String input){

        return redirectUrl(input == null ? "" : input);
    }

    /**
     * Sets the editProfile redirectUrl to the input without any changes.
     *
     * @param input The value to set, without any changes.
     *
     * @return this EditProfileFactory
     */
    public EditProfileFactory redirectUrl(final String input){

        editProfile.setRedirectUrl(input);
        return this;
    }

    /**
     * Sets the editProfile 'templateUrl' to an empty String.
     *
     * @return this EditProfileFactory
     */
    public EditProfileFactory templateUrl(){

        return templateUrlOrDefault(null);
    }

    /**
     * Sets the editProfile redirectUrl to the given input with whitespace trimmed off.
     * If the input is blank, then it sets the value to an empty {@link String}.
     *
     * @param input {@link String}
     *
     * @return this {@link EditProfileFactory}
     */
    public EditProfileFactory templateUrlOrDefault(final String input){

        return templateUrl(
            input == null
                ? ""
                : input.trim());
    }

    /**
     * Sets the editProfile 'templateUrl' to the input without any changes.
     *
     * @param input The value to set, without any changes.
     *
     * @return this EditProfileFactory
     */
    public EditProfileFactory templateUrl(final String input){

        editProfile.setTemplateUrl(input);
        return this;
    }

    /**
     * Sets the 'disabled' field to false.
     *
     * @return this {@link EditProfileFactory}
     */
    public EditProfileFactory enable(){

        editProfile.setDisabled(false);
        return this;
    }

    /**
     * Sets the 'disabled' field to true.
     *
     * @return this {@link EditProfileFactory}
     */
    public EditProfileFactory disable(){

        editProfile.setDisabled(true);
        return this;
    }

    /**
     * Creates a new instance of {@link EditProfilePolicy} which
     * contains the values set in the editProfile.
     *
     * @return EditProfilePolicy
     */
    public EditProfilePolicy build(){

        return new EditProfilePolicy(
            editProfile.getBasePath(),
            editProfile.getName(),
            editProfile.getRedirectUrl(),
            editProfile.getTemplateUrl(),
            editProfile.isDisabled()
        );
    }
}
