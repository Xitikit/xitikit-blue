package org.xitikit.blue.nommoc.errors.http.exceptions;

import org.xitikit.blue.nommoc.errors.http.BlueKitHttpException;
import org.xitikit.blue.nommoc.errors.http.ErrorCode;

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
