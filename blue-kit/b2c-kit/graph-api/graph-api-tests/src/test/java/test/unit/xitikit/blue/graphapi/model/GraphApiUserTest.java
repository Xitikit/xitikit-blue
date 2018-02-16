package test.unit.xitikit.blue.graphapi.model;

import org.junit.jupiter.api.Test;
import org.xitikit.blue.graphapi.model.GraphApiUser;
import org.xitikit.blue.graphapi.model.PasswordProfile;

import java.util.UUID;

import static java.util.Arrays.*;
import static org.junit.jupiter.api.Assertions.*;

class GraphApiUserTest{

    @Test
    void testWYSIWYG(){

        GraphApiUser user = new GraphApiUser();
        assertNull(user.getId());
        assertNull(user.getAccountEnabled());
        assertNull(user.getDisplayName());
        assertNull(user.getGivenName());
        assertNull(user.getSurname());
        assertNull(user.getPasswordProfile());
        assertNull(user.getUserPrincipalName());
        assertNull(user.getSignInNames());
        assertTrue(user.getAdditionalProperties().size() == 0);
        assertNull(user.getSignInEmail());

        user = testCase();
        assertNotNull(user.getId());
        assertTrue(user.getAccountEnabled());
        assertEquals("displayName", user.getDisplayName());
        assertEquals("givenName", user.getGivenName());
        assertEquals("surname", user.getSurname());
        assertNotNull(user.getPasswordProfile());
        assertEquals("emailAddress@something.xyz", user.getSignInEmail());
        assertTrue(user.getSignInNames().size() == 2);
        assertEquals("userPrincipalName", user.getUserPrincipalName());
        assertEquals("AdditionalProperty", user.getAdditionalProperties().get("AdditionalProperty"));
    }

    public static GraphApiUser testCase(){

        GraphApiUser user = new GraphApiUser(
            UUID.randomUUID().toString(),
            Boolean.TRUE,
            new PasswordProfile("super secret", false),
            asList(
                SignInNameTest.testCase(),
                SignInNameTest.testCaseGoogle()),
            "surname",
            "displayName",
            "givenName",
            "userPrincipalName"
        );
        user.setAdditionalProperty("AdditionalProperty", "AdditionalProperty");

        return user;
    }
}