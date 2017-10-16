package org.xitikit.blue.nommoc.errors.exceptions;

import org.xitikit.blue.nommoc.errors.BlueKitMethodException;
import org.xitikit.blue.nommoc.errors.ErrorCode;

import static org.xitikit.blue.nommoc.errors.ErrorCode.*;

/**
 * An exception used to indicate that a requested resource is forbidden
 * for all users.
 *
 * @author J. Keith Hoopes
 */
public class ForbiddenException extends BlueKitMethodException{

    private static final ErrorCode ERROR_CODE = FORBIDDEN;

    public ForbiddenException(){

        super(ERROR_CODE);
    }

    public ForbiddenException(final Throwable t){

        super(ERROR_CODE, t);
    }

    public ForbiddenException(final String... arguments){

        super(ERROR_CODE, arguments);
    }
}
