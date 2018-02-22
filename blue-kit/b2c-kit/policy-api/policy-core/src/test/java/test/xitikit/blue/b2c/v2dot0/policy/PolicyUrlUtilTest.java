package test.xitikit.blue.b2c.v2dot0.policy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.xitikit.blue.b2c.v2dot0.policy.PolicyUrlUtil.*;
import static org.xitikit.blue.b2c.v2dot0.policy.PolicyUrlUtil.Defaults.*;

class PolicyUrlUtilTest{

    @Test
    void testPolicyBase(){

        assertEquals("/blue-kit/sign-in", POLICY_BASE);
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