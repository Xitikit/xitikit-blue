package test.xitikit.blue.b2c.v2dot0.policy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.xitikit.blue.b2c.v2dot0.policy.PolicyUrlUtil.Defaults.*;
import static org.xitikit.blue.b2c.v2dot0.policy.PolicyUrlUtil.*;

class PolicyUrlUtilTest{

    @Test
    void testPOLICY_BASE(){

        assertEquals("/blue-kit/policy", POLICY_BASE);
    }

    @Test
    void testEDIT_PROFILE_BASE(){

        assertEquals(POLICY_BASE + "/edit-profile", EDIT_PROFILE_BASE);
    }

    @Test
    void testRESET_PASSWORD_BASE(){

        assertEquals(POLICY_BASE + "/reset-password", RESET_PASSWORD_BASE);
    }

    @Test
    void testSIGN_IN_BASE(){

        assertEquals(POLICY_BASE + "/sign-in", SIGN_IN_BASE);
    }

    @Test
    void testSIGN_OUT_BASE(){

        assertEquals(POLICY_BASE + "/sign-out", SIGN_OUT_BASE);
    }

    @Test
    void testSIGN_UP_BASE(){

        assertEquals(POLICY_BASE + "/sign-up", SIGN_UP_BASE);
    }

    @Test
    void testSIGN_UP_OR_SIGN_IN_BASE(){

        assertEquals(POLICY_BASE + "/sign-up-or-sign-in", SIGN_UP_OR_SIGN_IN_BASE);
    }

    @Test
    void testCombineRelativePaths(){

        //Should Ignore Blank Values
        assertEquals(SIGN_IN_BASE, combineRelativePaths("", null, " \t\n\r", SIGN_IN_BASE));
        assertEquals(SIGN_IN_BASE, combineRelativePaths(SIGN_IN_BASE, " \t\n\r", null, ""));
        //Should require leading '/'
        assertThrows(
            IllegalArgumentException.class,
            () -> combineRelativePaths(SIGN_IN_BASE, " \t\n\r", null, "bad-path"));
        //Should never allow trailing '/'
        assertThrows(
            IllegalArgumentException.class,
            () -> combineRelativePaths(SIGN_IN_BASE, " \t\n\r", null, "/bad-path/"));
        //Should never allow only '/'
        assertThrows(
            IllegalArgumentException.class,
            () -> combineRelativePaths(SIGN_IN_BASE, " \t\n\r", null, "/"));
    }
}