package itcase.xitikit.blue.b2c.api.v2dot0.policy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.xitikit.blue.b2c.api.v2dot0.policy.itcase.ITCaseApplication;
import org.xitikit.blue.b2c.v2dot0.policy.B2cPolicyConfiguration;
import org.xitikit.blue.b2c.v2dot0.policy.SignInPolicy;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Keith on 10/15/2017.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ITCaseApplication.class, webEnvironment = WebEnvironment.MOCK)
class PolicyITCase{

    @Autowired
    private B2cPolicyConfiguration b2cPolicyConfiguration;

    @Test
    void signInConfiguration(){

        SignInPolicy signInPolicy;
        assertNotNull(b2cPolicyConfiguration);

        signInPolicy = b2cPolicyConfiguration.getSignIn();
        assertNotNull(signInPolicy);

        assertEquals("one", signInPolicy.getName());
        assertEquals("http://test/", signInPolicy.getRedirectUrl());
        assertEquals("", signInPolicy.getTemplateUrl());
        assertFalse(signInPolicy.isDisabled());

        signInPolicy = b2cPolicyConfiguration.getSignIn();
        assertNotNull(signInPolicy, "signInPolicy should not be null, even when disabled.");
        assertEquals("", signInPolicy.getName());
        assertEquals("", signInPolicy.getRedirectUrl());
        assertEquals("", signInPolicy.getTemplateUrl());
        assertTrue(signInPolicy.isDisabled());
    }

}