package org.xitikit.blue.nommoc.errors.http.exceptions;

import org.xitikit.blue.nommoc.errors.http.BlueKitHttpException;
import org.xitikit.blue.nommoc.errors.http.ErrorCode;

import static org.xitikit.blue.nommoc.errors.http.ErrorCode.*;

/**
 * @author J. Keith Hoopes
 */
public class UnauthorizedException extends BlueKitHttpException{

    private static final ErrorCode ERROR_CODE = UNAUTHORIZED;

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
