package org.xitikit.blue.nommoc.errors;

/**
 * Copyright ${year}
 *
 * A restricted/limited set of error codes that correspond
 * to standard http error codes.
 *
 * @author J. Keith Hoopes
 */
public enum ErrorCode{

    BAD_REQUEST(400, "Bad Request"),
    FORBIDDEN(403, "Forbidden"),
    I_AM_A_TEAPOT(418, "I'm a teapot"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    NOT_FOUND(404, "Not Found"),
    NOT_IMPLEMENTED(501, "Not Implemented"),
    UNAUTHORIZED(401, "Unauthorized");

    private final int value;

    private final String description;

    ErrorCode(final int value, final String description){

        this.value = value;
        this.description = description;
    }

    /**
     * Return the integer value of this error code.
     */
    public int value(){

        return this.value;
    }

    /**
     * Return the description of this error code.
     */
    public String getDescription(){

        return this.description;
    }
}
