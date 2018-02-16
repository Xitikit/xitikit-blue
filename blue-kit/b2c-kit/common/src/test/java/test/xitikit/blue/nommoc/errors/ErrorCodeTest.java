package test.xitikit.blue.nommoc.errors;

import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.*;
import static org.xitikit.blue.nommoc.errors.http.ErrorCode.*;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
class ErrorCodeTest{

    @Test
    void test(){

        assertEquals("Bad Request", BAD_REQUEST.getDescription());
        assertEquals(400, BAD_REQUEST.value());

        assertEquals("Forbidden", FORBIDDEN.getDescription());
        assertEquals(403, FORBIDDEN.value());

        assertEquals("I'm a teapot", I_AM_A_TEAPOT.getDescription());
        assertEquals(418, I_AM_A_TEAPOT.value());

        assertEquals("Internal Server Error", INTERNAL_SERVER_ERROR.getDescription());
        assertEquals(500, INTERNAL_SERVER_ERROR.value());

        assertEquals("Method Not Allowed", METHOD_NOT_ALLOWED.getDescription());
        assertEquals(405, METHOD_NOT_ALLOWED.value());

        assertEquals("Not Found", NOT_FOUND.getDescription());
        assertEquals(404, NOT_FOUND.value());

        assertEquals("Not Implemented", NOT_IMPLEMENTED.getDescription());
        assertEquals(501, NOT_IMPLEMENTED.value());

        assertEquals("Unauthorized", UNAUTHORIZED.getDescription());
        assertEquals(401, UNAUTHORIZED.value());
    }
}