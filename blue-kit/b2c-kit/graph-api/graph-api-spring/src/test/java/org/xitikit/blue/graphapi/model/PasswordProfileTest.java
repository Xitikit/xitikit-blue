package org.xitikit.blue.graphapi.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordProfileTest{

    public static PasswordProfile testCase(){

        PasswordProfile passwordProfile = new PasswordProfile(" whatever with a space in front", true);
        passwordProfile.setAdditionalProperty("something", "something with a space at the end ");
        return passwordProfile;
    }

    @Test
    void testWYSIWYG(){

        PasswordProfile passwordProfile = new PasswordProfile();
        assertNull(passwordProfile.getPassword());
        assertNull(passwordProfile.getForceChangePasswordNextLogin());
        assertNotNull(passwordProfile.getAdditionalProperties());
        assertTrue(passwordProfile.getAdditionalProperties().size() == 0);

        passwordProfile = testCase();
        assertEquals(" whatever with a space in front", passwordProfile.getPassword());
        assertTrue(passwordProfile.getForceChangePasswordNextLogin());
        assertEquals("something with a space at the end ", passwordProfile.getAdditionalProperties().get("something"));
    }
}
