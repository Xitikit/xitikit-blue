package org.xitikit.blue.nommoc.errors.http.exceptions;

import org.xitikit.blue.nommoc.errors.http.BlueKitHttpException;
import org.xitikit.blue.nommoc.errors.http.ErrorCode;

import static org.xitikit.blue.nommoc.errors.http.ErrorCode.*;

/**
 * An exception used to indicate that a requested resource is forbidden
 * for all users.
 *
 * @author J. Keith Hoopes
 */
public class ForbiddenException extends BlueKitHttpException{

  private static final ErrorCode ERROR_CODE = FORBIDDEN;

  public ForbiddenException(){

    super(ERROR_CODE);
  }

  public ForbiddenException(final Throwable t){

    super(ERROR_CODE, t);
  }

  public ForbiddenException(final String... arguments){

    super(ERROR_CODE, arguments);
  }
}
