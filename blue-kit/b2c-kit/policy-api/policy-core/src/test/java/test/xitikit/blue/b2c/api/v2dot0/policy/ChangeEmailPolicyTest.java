package test.xitikit.blue.b2c.api.v2dot0.policy;

import org.junit.jupiter.api.Test;
import org.xitikit.blue.b2c.kit.v2dot0.policy.ChangeEmailPolicy;
import org.xitikit.blue.b2c.kit.v2dot0.policy.PolicyForB2C;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Keith on 10/15/2017.
 */
class ChangeEmailPolicyTest{

    private final ChangeEmailPolicy a = new ChangeEmailPolicy();

    private final PolicyForB2C b = a;

    @Test
    void Name(){

        b.setName("test");
        assertEquals("", b.getName());
    }

    @Test
    void RedirectUrl(){

        b.setRedirectUrl("test");
        assertEquals("", b.getRedirectUrl());
    }

    @Test
    void TemplateUrl(){

        b.setTemplateUrl("test");
        assertEquals("", b.getTemplateUrl());
    }

    @Test
    void Disabled(){

        b.setDisabled(false);
        assertTrue(b.isDisabled());
    }
}