package org.xitikit.blue.nommoc.errors.exceptions;

import org.xitikit.blue.nommoc.errors.BlueKitMethodException;
import org.xitikit.blue.nommoc.errors.ErrorCode;

import static org.xitikit.blue.nommoc.errors.ErrorCode.*;

/**
 * @author J. Keith Hoopes
 */
public class NotFoundException extends BlueKitMethodException{

    private static final ErrorCode ERROR_CODE = NOT_FOUND;

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
