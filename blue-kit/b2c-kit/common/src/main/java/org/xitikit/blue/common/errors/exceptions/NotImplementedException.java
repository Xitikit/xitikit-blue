package org.xitikit.blue.common.errors.exceptions;

import org.xitikit.blue.common.errors.BlueKitHttpException;
import org.xitikit.blue.common.errors.ErrorCode;

/**
 * @author J. Keith Hoopes
 */
public class NotImplementedException extends BlueKitHttpException{

    private static final ErrorCode ERROR_CODE = ErrorCode.UNAUTHORIZED;

    public NotImplementedException(){

        super(ERROR_CODE);
    }

    public NotImplementedException(final String... args){

        super(ERROR_CODE, args);
    }

    public NotImplementedException(final Throwable t){

        super(ERROR_CODE, t);
    }
}
