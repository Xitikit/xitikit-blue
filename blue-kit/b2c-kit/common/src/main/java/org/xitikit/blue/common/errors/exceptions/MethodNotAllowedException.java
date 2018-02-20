package org.xitikit.blue.common.errors.exceptions;

import org.xitikit.blue.common.errors.BlueKitHttpException;
import org.xitikit.blue.common.errors.ErrorCode;

/**
 * @author J. Keith Hoopes
 */
public class MethodNotAllowedException extends BlueKitHttpException{

    private static final ErrorCode ERROR_CODE = ErrorCode.METHOD_NOT_ALLOWED;

    public MethodNotAllowedException(){

        super(ERROR_CODE);
    }

    public MethodNotAllowedException(final String... args){

        super(ERROR_CODE, args);
    }

    public MethodNotAllowedException(final Throwable t){

        super(ERROR_CODE, t);
    }
}
