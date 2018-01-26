package org.xitikit.blue.nommoc.errors.http.exceptions;

import org.xitikit.blue.nommoc.errors.http.BlueKitHttpException;
import org.xitikit.blue.nommoc.errors.http.ErrorCode;

/**
 * @author J. Keith Hoopes
 */
public class IAmATeaPotException extends BlueKitHttpException{

  private static final ErrorCode ERROR_CODE = ErrorCode.UNAUTHORIZED;

  public IAmATeaPotException(){

    super(ERROR_CODE);
  }

  public IAmATeaPotException(final String... args){

    super(ERROR_CODE, args);
  }

  public IAmATeaPotException(final Throwable t){

    super(ERROR_CODE, t);
  }
}
