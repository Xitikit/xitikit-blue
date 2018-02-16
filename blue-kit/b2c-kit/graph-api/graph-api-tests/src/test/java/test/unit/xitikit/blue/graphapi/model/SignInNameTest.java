package test.unit.xitikit.blue.graphapi.model;

import org.junit.jupiter.api.Test;
import org.xitikit.blue.graphapi.model.SignInName;

import static org.junit.jupiter.api.Assertions.*;

class SignInNameTest{

    public static SignInName testCaseGoogle(){

        SignInName signInName = new SignInName("google", "email@gmail.com");
        signInName.setAdditionalProperty("something", "whatever");
        return signInName;
    }

    @Test
    void testWISYWIG(){

        SignInName signInName = new SignInName();
        assertNull(signInName.getType());
        assertNull(signInName.getValue());
        assertNotNull(signInName.getAdditionalProperties());
        assertTrue(signInName.getAdditionalProperties().size() == 0);

        signInName = testCase();
        assertEquals("emailAddress", signInName.getType());
        assertEquals("emailAddress@something.xyz", signInName.getValue());

        signInName.setAdditionalProperty("something", "whatever");
        assertEquals("whatever", signInName.getAdditionalProperties().get("something"));

        signInName.setType("test");
        signInName.setValue("test");
        assertEquals("test", signInName.getType());
        assertEquals("test", signInName.getValue());
    }

    public static SignInName testCase(){

        SignInName signInName = new SignInName("emailAddress", "emailAddress@something.xyz");
        signInName.setAdditionalProperty("something", "whatever");

        return signInName;
    }
}