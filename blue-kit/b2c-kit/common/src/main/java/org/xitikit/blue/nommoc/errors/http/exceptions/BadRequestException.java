package org.xitikit.blue.nommoc.errors.http.exceptions;

import org.xitikit.blue.nommoc.errors.http.BlueKitHttpException;
import org.xitikit.blue.nommoc.errors.http.ErrorCode;

/**
 * @author J. Keith Hoopes
 */
public class BadRequestException extends BlueKitHttpException{

    private static final ErrorCode ERROR_CODE = ErrorCode.BAD_REQUEST;

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
