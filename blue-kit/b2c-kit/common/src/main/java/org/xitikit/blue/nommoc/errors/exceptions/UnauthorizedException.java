package org.xitikit.blue.nommoc.errors.exceptions;

import org.xitikit.blue.nommoc.errors.BlueKitMethodException;
import org.xitikit.blue.nommoc.errors.ErrorCode;

import static org.xitikit.blue.nommoc.errors.ErrorCode.*;

/**
 * @author J. Keith Hoopes
 */
public class UnauthorizedException extends BlueKitMethodException{

  private static final ErrorCode ERROR_CODE = UNAUTHORIZED;

  public UnauthorizedException(){

    super(ERROR_CODE);
  }

  public UnauthorizedException(final String... args){

    super(ERROR_CODE, args);
  }

  public UnauthorizedException(final Throwable t){

    super(ERROR_CODE, t);
  }
}
