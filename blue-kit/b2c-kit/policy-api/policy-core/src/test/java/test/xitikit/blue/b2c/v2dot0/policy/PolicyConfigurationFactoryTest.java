package test.xitikit.blue.b2c.v2dot0.policy;

import org.junit.jupiter.api.Test;
import org.xitikit.blue.b2c.v2dot0.policy.PolicyConfiguration;
import org.xitikit.blue.b2c.v2dot0.policy.PolicyConfigurationFactory;

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
    void disableEverything(){

        PolicyConfiguration target = PolicyConfigurationFactory.instance().disableEverything().build();

        assertNotNull(target.getEditProfile());
        assertEquals("", target.getEditProfile().getName());
        assertEquals("", target.getEditProfile().getRedirectUrl());
        assertEquals("", target.getEditProfile().getTemplateUrl());
        assertTrue(target.getEditProfile().isDisabled());

        assertNotNull(target.getResetPassword());
        assertEquals("", target.getResetPassword().getName());
        assertEquals("", target.getResetPassword().getRedirectUrl());
        assertEquals("", target.getResetPassword().getTemplateUrl());
        assertTrue(target.getResetPassword().isDisabled());

        assertNotNull(target.getSignIn());
        assertEquals("", target.getSignIn().getName());
        assertEquals("", target.getSignIn().getRedirectUrl());
        assertNull(target.getSignIn().getTemplateUrl());//sign-in template url is always null
        assertTrue(target.getSignIn().isDisabled());

        assertNotNull(target.getSignOut());
        assertEquals("", target.getSignOut().getName());
        assertEquals("", target.getSignOut().getRedirectUrl());
        assertNull(target.getSignOut().getTemplateUrl());
        assertTrue(target.getSignOut().isDisabled());

        assertNotNull(target.getSignUp());
        assertEquals("", target.getSignUp().getName());
        assertEquals("", target.getSignUp().getRedirectUrl());
        assertEquals("", target.getSignUp().getTemplateUrl());
        assertTrue(target.getSignUp().isDisabled());

        assertNotNull(target.getSignUpOrSignIn());
        assertEquals("", target.getSignUpOrSignIn().getName());
        assertEquals("", target.getSignUpOrSignIn().getRedirectUrl());
        assertEquals("", target.getSignUpOrSignIn().getTemplateUrl());
        assertTrue(target.getSignUpOrSignIn().isDisabled());
    }

    @Test
    void disableEditProfile(){

        PolicyConfiguration target = PolicyConfigurationFactory
            .instance()
            .disableEditProfile()
            .build();
        assertNotNull(target.getEditProfile());
        assertEquals("", target.getEditProfile().getName());
        assertEquals("", target.getEditProfile().getRedirectUrl());
        assertEquals("", target.getEditProfile().getTemplateUrl());
        assertTrue(target.getEditProfile().isDisabled());
    }

    @Test
    void disableResetPassword(){

        PolicyConfiguration target = PolicyConfigurationFactory
            .instance()
            .disableResetPassword()
            .build();
        assertNotNull(target.getResetPassword());
        assertEquals("", target.getResetPassword().getName());
        assertEquals("", target.getResetPassword().getRedirectUrl());
        assertEquals("", target.getResetPassword().getTemplateUrl());
        assertTrue(target.getResetPassword().isDisabled());
    }

    @Test
    void disableSignIn(){

        PolicyConfiguration target = PolicyConfigurationFactory
            .instance()
            .disableSignIn()
            .build();
        assertNotNull(target.getSignIn());
        assertEquals("", target.getSignIn().getName());
        assertEquals("", target.getSignIn().getRedirectUrl());
        assertNull(target.getSignIn().getTemplateUrl());
        assertTrue(target.getSignIn().isDisabled());
    }

    @Test
    void disableSignOut(){

        PolicyConfiguration target = PolicyConfigurationFactory
            .instance()
            .disableSignOut()
            .build();
        assertNotNull(target.getSignOut());
        assertEquals("", target.getSignOut().getName());
        assertEquals("", target.getSignOut().getRedirectUrl());
        assertNull(target.getSignOut().getTemplateUrl());
        assertTrue(target.getSignOut().isDisabled());
    }

    @Test
    void disableSignUp(){

        PolicyConfiguration target = PolicyConfigurationFactory
            .instance()
            .disableSignUp()
            .build();
        assertNotNull(target.getSignUp());
        assertEquals("", target.getSignUp().getName());
        assertEquals("", target.getSignUp().getRedirectUrl());
        assertEquals("", target.getSignUp().getTemplateUrl());
        assertTrue(target.getSignUp().isDisabled());
    }

    @Test
    void disableSignUpOrSignIn(){

        PolicyConfiguration target = PolicyConfigurationFactory
            .instance()
            .disableSignUpOrSignIn()
            .build();
        assertNotNull(target.getSignUpOrSignIn());
        assertEquals("", target.getSignUpOrSignIn().getName());
        assertEquals("", target.getSignUpOrSignIn().getRedirectUrl());
        assertEquals("", target.getSignUpOrSignIn().getTemplateUrl());
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