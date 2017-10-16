package org.xitikit.blue.nommoc.errors.exceptions;

import org.xitikit.blue.nommoc.errors.BlueKitMethodException;
import org.xitikit.blue.nommoc.errors.ErrorCode;

import static org.xitikit.blue.nommoc.errors.ErrorCode.*;

/**
 * @author J. Keith Hoopes
 */
public class InternalServerErrorException extends BlueKitMethodException{

    private static final ErrorCode ERROR_CODE = UNAUTHORIZED;

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
