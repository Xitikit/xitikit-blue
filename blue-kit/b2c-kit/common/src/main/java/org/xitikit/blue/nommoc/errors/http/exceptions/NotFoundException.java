package org.xitikit.blue.nommoc.errors.http.exceptions;

import org.xitikit.blue.nommoc.errors.http.BlueKitHttpException;
import org.xitikit.blue.nommoc.errors.http.ErrorCode;

import static org.xitikit.blue.nommoc.errors.http.ErrorCode.*;

/**
 * @author J. Keith Hoopes
 */
public class NotFoundException extends BlueKitHttpException{

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
