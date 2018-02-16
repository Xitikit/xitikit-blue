package test.unit.xitikit.blue.graphapi.model;

import org.junit.jupiter.api.Test;
import org.xitikit.blue.graphapi.model.PaginatedUsers;

import java.util.Collections;

import static java.util.Arrays.*;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("WeakerAccess")
class PaginatedUsersTest{

    @Test
    void testWYSIWYG(){

        PaginatedUsers paginatedUsers = new PaginatedUsers();
        assertNull(paginatedUsers.getMetaData());
        assertNull(paginatedUsers.getSkiptoken());
        assertNull(paginatedUsers.getUsers());
        assertTrue(paginatedUsers.getAdditionalProperties().size() == 0);
        assertNotNull(paginatedUsers.getAdditionalProperties());
        assertTrue(paginatedUsers.getAdditionalProperties().size() == 0);

        paginatedUsers = testCase();
        assertEquals("{odata:'something'}", paginatedUsers.getMetaData());
        assertEquals("http://something/whatever$skiptoken=1", paginatedUsers.getNextLink());
        assertEquals("1", paginatedUsers.getSkiptoken());
        assertTrue(paginatedUsers.getUsers().size() == 2);
        assertTrue(paginatedUsers.getAdditionalProperties().size() == 1);
        assertEquals("value", paginatedUsers.getAdditionalProperties().get("unexpected"));

        paginatedUsers.setAdditionalProperty("another", "surprise");
        assertEquals("surprise", paginatedUsers.getAdditionalProperties().get("another"));

        paginatedUsers.setUsers(Collections.emptyList());
        assertTrue(paginatedUsers.getUsers().size() == 0);

        paginatedUsers.setMetaData("{'not_actually_sure':'what parameter values this might have'}");
        assertEquals("{'not_actually_sure':'what parameter values this might have'}", paginatedUsers.getMetaData());

        paginatedUsers.setNextLink("http://something/without.a.skip.token");
        assertEquals("http://something/without.a.skip.token", paginatedUsers.getNextLink());
        assertNull(paginatedUsers.getSkiptoken());
    }

    public static PaginatedUsers testCase(){

        PaginatedUsers paginatedUsers = new PaginatedUsers(
            asList(
                GraphApiUserTest.testCase(),
                GraphApiUserTest.testCase()
            ),
            "http://something/whatever$skiptoken=1",
            "{odata:'something'}");
        paginatedUsers.setAdditionalProperty("unexpected", "value");

        return paginatedUsers;
    }
}