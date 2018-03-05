package test.xitikit.blue.b2c.v2dot0.policy;

import org.junit.jupiter.api.Test;
import org.xitikit.blue.b2c.v2dot0.policy.*;
import org.xitikit.blue.b2c.v2dot0.policy.factories.PolicyConfigurationFactory;

import static org.junit.jupiter.api.Assertions.*;

class PolicyConfigurationFactoryTest{

    @Test
    void instance(){

        assertNotNull(PolicyConfigurationFactory.instance());
        //instance should be a new reference each time
        assertFalse(PolicyConfigurationFactory.instance() == PolicyConfigurationFactory.instance());
    }

    @Test
    void of(){

        PolicyConfigurationFactory factory = PolicyConfigurationFactory.instance();
        PolicyConfiguration policyConfiguration = factory.build();

        PolicyConfigurationFactory factory2 = PolicyConfigurationFactory.of(policyConfiguration);

        assertFalse(factory == factory2);//checking reference, not sameness
        //noinspection ObjectEquality
        assertFalse(policyConfiguration == factory2.build());//checking reference, not sameness
    }

    @Test
    void defaultValues(){

        PolicyConfiguration target = PolicyConfigurationFactory
            .instance()
            .defaultValues()
            .build();

        assertNotNull(target.getEditProfile());
        assertEquals(PolicyUrlUtil.Defaults.EDIT_PROFILE_BASE, target.getEditProfile().getBasePath());
        assertEquals("b2c_1_edit_profile", target.getEditProfile().getName());
        assertEquals("", target.getEditProfile().getRedirectUrl());
        assertEquals("", target.getEditProfile().getTemplateUrl());
        assertFalse(target.getEditProfile().isDisabled());

        assertNotNull(target.getResetPassword());
        assertEquals(PolicyUrlUtil.Defaults.RESET_PASSWORD_BASE, target.getResetPassword().getBasePath());
        assertEquals("b2c_1_reset_password", target.getResetPassword().getName());
        assertEquals("", target.getResetPassword().getRedirectUrl());
        assertEquals("", target.getResetPassword().getTemplateUrl());
        assertFalse(target.getResetPassword().isDisabled());

        assertNotNull(target.getSignIn());
        assertEquals(PolicyUrlUtil.Defaults.SIGN_IN_BASE, target.getSignIn().getBasePath());
        assertEquals("b2c_1_sign_in", target.getSignIn().getName());
        assertEquals("", target.getSignIn().getRedirectUrl());
        assertNull(target.getSignIn().getTemplateUrl());//sign-in template url is always null
        assertFalse(target.getSignIn().isDisabled());

        assertNotNull(target.getSignOut());
        assertEquals(PolicyUrlUtil.Defaults.SIGN_OUT_BASE, target.getSignOut().getBasePath());
        assertEquals("b2c_1_sign_out", target.getSignOut().getName());
        assertEquals("", target.getSignOut().getRedirectUrl());
        assertNull(target.getSignOut().getTemplateUrl());
        assertFalse(target.getSignOut().isDisabled());

        assertNotNull(target.getSignUp());
        assertEquals("b2c_1_sign_up", target.getSignUp().getName());
        assertEquals("", target.getSignUp().getRedirectUrl());
        assertEquals("", target.getSignUp().getTemplateUrl());
        assertFalse(target.getSignUp().isDisabled());

        assertNotNull(target.getSignUpOrSignIn());
        assertEquals("b2c_1_sign_up_or_sign_in", target.getSignUpOrSignIn().getName());
        assertEquals("", target.getSignUpOrSignIn().getRedirectUrl());
        assertEquals("", target.getSignUpOrSignIn().getTemplateUrl());
        assertFalse(target.getSignUpOrSignIn().isDisabled());
    }

    @Test
    void editProfile(){

        EditProfilePolicy target = PolicyConfigurationFactory
            .instance()
            .editProfile()
            .defaultValues()
            .and().build()
            .getEditProfile();

        assertNotNull(target);
        assertEquals(PolicyUrlUtil.Defaults.EDIT_PROFILE_BASE, target.getBasePath());
        assertEquals("b2c_1_edit_profile", target.getName());
        assertEquals("", target.getRedirectUrl());
        assertEquals("", target.getTemplateUrl());
        assertFalse(target.isDisabled());
    }

    @Test
    void resetPassword(){

        ResetPasswordPolicy target = PolicyConfigurationFactory
            .instance()
            .resetPassword()
            .defaultValues()
            .and()
            .build().getResetPassword();

        assertNotNull(target);
        assertEquals(PolicyUrlUtil.Defaults.RESET_PASSWORD_BASE, target.getBasePath());
        assertEquals("b2c_1_reset_password", target.getName());
        assertEquals("", target.getRedirectUrl());
        assertEquals("", target.getTemplateUrl());
        assertFalse(target.isDisabled());
    }

    @Test
    void signIn(){

        SignInPolicy target = PolicyConfigurationFactory
            .instance()
            .signIn()
            .defaultValues()
            .and().build()
            .getSignIn();

        assertNotNull(target);
        assertEquals(PolicyUrlUtil.Defaults.SIGN_IN_BASE, target.getBasePath());
        assertEquals("b2c_1_sign_in", target.getName());
        assertEquals("", target.getRedirectUrl());
        assertNull(target.getTemplateUrl());
        assertFalse(target.isDisabled());
    }

    @Test
    void signOut(){

        SignOutPolicy target = PolicyConfigurationFactory
            .instance()
            .signOut()
            .defaultValues()
            .and().build()
            .getSignOut();

        assertNotNull(target);
        assertEquals(PolicyUrlUtil.Defaults.SIGN_OUT_BASE, target.getBasePath());
        assertEquals("b2c_1_sign_out", target.getName());
        assertEquals("", target.getRedirectUrl());
        assertNull(target.getTemplateUrl());
        assertFalse(target.isDisabled());
    }

    @Test
    void signUp(){

        SignUpPolicy target = PolicyConfigurationFactory
            .instance()
            .signUp()
            .defaultValues()
            .and()
            .build().getSignUp();

        assertNotNull(target);
        assertEquals(PolicyUrlUtil.Defaults.SIGN_UP_BASE, target.getBasePath());
        assertEquals("b2c_1_sign_up", target.getName());
        assertEquals("", target.getRedirectUrl());
        assertEquals("", target.getTemplateUrl());
        assertFalse(target.isDisabled());
    }

    @Test
    void signUpOrSignIn(){

        SignUpOrSignInPolicy target = PolicyConfigurationFactory
            .instance()
            .signUpOrSignIn()
            .defaultValues()
            .and().build()
            .getSignUpOrSignIn();

        assertNotNull(target);
        assertEquals(PolicyUrlUtil.Defaults.SIGN_UP_OR_SIGN_IN_BASE, target.getBasePath());
        assertEquals("b2c_1_sign_up_or_sign_in", target.getName());
        assertEquals("", target.getRedirectUrl());
        assertEquals("", target.getTemplateUrl());
        assertFalse(target.isDisabled());
    }
    @Test
    void disableAll_ShouldOnlyDisable(){

        PolicyConfiguration target = PolicyConfigurationFactory
            .instance()
            .disableAll()
            .build();

        assertNotNull(target.getEditProfile());
        assertNull(target.getEditProfile().getBasePath());
        assertNull(target.getEditProfile().getName());
        assertNull(target.getEditProfile().getRedirectUrl());
        assertNull(target.getEditProfile().getTemplateUrl());
        assertTrue(target.getEditProfile().isDisabled());

        assertNotNull(target.getResetPassword());
        assertNull(target.getResetPassword().getBasePath());
        assertNull(target.getResetPassword().getName());
        assertNull(target.getResetPassword().getRedirectUrl());
        assertNull(target.getResetPassword().getTemplateUrl());
        assertTrue(target.getResetPassword().isDisabled());

        assertNotNull(target.getSignIn());
        assertNull(target.getSignIn().getBasePath());
        assertNull(target.getSignIn().getName());
        assertNull(target.getSignIn().getRedirectUrl());
        assertNull(target.getSignIn().getTemplateUrl());//sign-in template url is always null
        assertTrue(target.getSignIn().isDisabled());

        assertNotNull(target.getSignOut());
        assertNull(target.getSignOut().getBasePath());
        assertNull(target.getSignOut().getName());
        assertNull(target.getSignOut().getRedirectUrl());
        assertNull(target.getSignOut().getTemplateUrl());
        assertTrue(target.getSignOut().isDisabled());

        assertNotNull(target.getSignUp());
        assertNull(target.getSignUp().getName());
        assertNull(target.getSignUp().getRedirectUrl());
        assertNull(target.getSignUp().getTemplateUrl());
        assertTrue(target.getSignUp().isDisabled());

        assertNotNull(target.getSignUpOrSignIn());
        assertNull(target.getSignUpOrSignIn().getName());
        assertNull(target.getSignUpOrSignIn().getRedirectUrl());
        assertNull(target.getSignUpOrSignIn().getTemplateUrl());
        assertTrue(target.getSignUpOrSignIn().isDisabled());
    }

    /**
     * Each call to build should return a new reference.
     */
    @Test
    void build(){

        PolicyConfigurationFactory factory = PolicyConfigurationFactory.instance();
        // Each call to build should return a new reference, so this
        // reference check is intentional.
        // noinspection ObjectEquality
        assertFalse(factory.build() == factory.build());
    }
}