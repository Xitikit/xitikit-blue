package org.xitikit.blue.nommoc.errors.exceptions;

import org.xitikit.blue.nommoc.errors.BlueKitMethodException;
import org.xitikit.blue.nommoc.errors.ErrorCode;

import static org.xitikit.blue.nommoc.errors.ErrorCode.*;

/**
 * @author J. Keith Hoopes
 */
public class BadRequestException extends BlueKitMethodException{

    private static final ErrorCode ERROR_CODE = BAD_REQUEST;

    public BadRequestException(){

        super(ERROR_CODE);
    }

    public BadRequestException(final String... arguments){

        super(ERROR_CODE, arguments);
    }

    public BadRequestException(final Throwable t){

        super(ERROR_CODE, t);
    }
}
