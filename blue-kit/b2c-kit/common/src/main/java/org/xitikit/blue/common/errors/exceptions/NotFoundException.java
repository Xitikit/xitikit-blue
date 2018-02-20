package org.xitikit.blue.common.errors.exceptions;

import org.xitikit.blue.common.errors.BlueKitHttpException;
import org.xitikit.blue.common.errors.ErrorCode;

/**
 * @author J. Keith Hoopes
 */
public class NotFoundException extends BlueKitHttpException{

    private static final ErrorCode ERROR_CODE = ErrorCode.NOT_FOUND;

    public NotFoundException(){

        super(ERROR_CODE);
    }

    public NotFoundException(final String... args){

        super(ERROR_CODE, args);
    }

    public NotFoundException(final Throwable t){

        super(ERROR_CODE, t);
    }
}
