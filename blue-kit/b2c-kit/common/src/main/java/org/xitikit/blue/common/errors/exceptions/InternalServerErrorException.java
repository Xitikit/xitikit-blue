package org.xitikit.blue.common.errors.exceptions;

import org.xitikit.blue.common.errors.BlueKitHttpException;
import org.xitikit.blue.common.errors.ErrorCode;

/**
 * @author J. Keith Hoopes
 */
public class InternalServerErrorException extends BlueKitHttpException{

    private static final ErrorCode ERROR_CODE = ErrorCode.UNAUTHORIZED;

    public InternalServerErrorException(){

        super(ERROR_CODE);
    }

    public InternalServerErrorException(final String... args){

        super(ERROR_CODE, args);
    }

    public InternalServerErrorException(final Throwable t){

        super(ERROR_CODE, t);
    }
}
