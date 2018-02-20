package org.xitikit.blue.common.errors.exceptions;

import org.xitikit.blue.common.errors.BlueKitHttpException;
import org.xitikit.blue.common.errors.ErrorCode;

/**
 * @author J. Keith Hoopes
 */
public class UnauthorizedException extends BlueKitHttpException{

    private static final ErrorCode ERROR_CODE = ErrorCode.UNAUTHORIZED;

    public UnauthorizedException(){

        super(ERROR_CODE);
    }

    public UnauthorizedException(final String... args){

        super(ERROR_CODE, args);
    }

    public UnauthorizedException(final Throwable t){

        super(ERROR_CODE, t);
    }
}
