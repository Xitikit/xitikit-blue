package org.xitikit.blue.nommoc.errors.http.exceptions;

import org.xitikit.blue.nommoc.errors.http.BlueKitHttpException;
import org.xitikit.blue.nommoc.errors.http.ErrorCode;

import static org.xitikit.blue.nommoc.errors.http.ErrorCode.*;

/**
 * @author J. Keith Hoopes
 */
public class NotImplementedException extends BlueKitHttpException{

    private static final ErrorCode ERROR_CODE = UNAUTHORIZED;

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
