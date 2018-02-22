package test.xitikit.blue.b2c.v2dot0.policy;

import org.junit.jupiter.api.Test;
import org.xitikit.blue.b2c.v2dot0.policy.ChangeEmailPolicy;
import org.xitikit.blue.b2c.v2dot0.policy.PolicyForB2C;

import static org.junit.jupiter.api.Assertions.*;

/**
 * As of the date of creation, Azure AD B2C does not support changing the email
 * through a policy (such as the EditProfilePolicyA). To change a users email,
 * the graph-api module should be used to create a custom programatic approach
 * to email changes.
 * <p>
 * Copyright Xitkit.org ${year}
 *
 * @author Keith Hoopes
 */
class ChangeEmailPolicyTest{

    private final ChangeEmailPolicy a = new ChangeEmailPolicy();

    private final PolicyForB2C b = a;

    @Test
    void Name(){

        b.setName("test");
        assertNull(b.getName());
    }

    @Test
    void RedirectUrl(){

        b.setRedirectUrl("test");
        assertNull(b.getRedirectUrl());
    }

    @Test
    void TemplateUrl(){

        b.setTemplateUrl("test");
        assertNull(b.getTemplateUrl());
    }

    @Test
    void Disabled(){

        b.setDisabled(false);
        assertTrue(b.isDisabled());
    }
}