package org.xitikit.blue.nommoc.errors.exceptions;

import org.xitikit.blue.nommoc.errors.BlueKitMethodException;
import org.xitikit.blue.nommoc.errors.ErrorCode;

import static org.xitikit.blue.nommoc.errors.ErrorCode.*;

/**
 * @author J. Keith Hoopes
 */
public class MethodNotAllowedException extends BlueKitMethodException{

  private static final ErrorCode ERROR_CODE = METHOD_NOT_ALLOWED;

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
