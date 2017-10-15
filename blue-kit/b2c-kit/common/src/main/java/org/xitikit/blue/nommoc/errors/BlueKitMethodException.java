package org.xitikit.blue.nommoc.errors;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.*;

/**
 * @author J. Keith Hoopes
 */
public abstract class BlueKitMethodException extends RuntimeException{

  private final ErrorCode errorCode;

  private final List<String> arguments = new ArrayList<>();

  protected BlueKitMethodException(final ErrorCode errorCode){

    Verify.notNull(errorCode, "'errorCode' was null in 'org.xitikit.blue.nommoc.errors.BlueKitMethodException::BlueKitMethodException(final ErrorCode errorCode)'.");
    this.errorCode = errorCode;
  }

  protected BlueKitMethodException(final ErrorCode errorCode, final Throwable throwable){

    super(throwable);

    Verify.notNull(errorCode, "'errorCode' was null in 'org.xitikit.blue.nommoc.errors.BlueKitMethodException::BlueKitMethodException(final ErrorCode errorCode, final Throwable t)'.");
    Verify.notNull(errorCode, "'throwable' was null in 'org.xitikit.blue.nommoc.errors.BlueKitMethodException::BlueKitMethodException(final ErrorCode errorCode, final Throwable t)'.");

    this.errorCode = errorCode;
  }

  protected BlueKitMethodException(final ErrorCode errorCode, final String... arguments){

    Verify.notNull(errorCode, "'errorCode' was null in 'org.xitikit.blue.nommoc.errors.BlueKitMethodException::BlueKitMethodException(final ErrorCode errorCode, final String... arguments)'.");
    Verify.notNull(arguments, "'arguments' were null in 'org.xitikit.blue.nommoc.errors.BlueKitMethodException::BlueKitMethodException(final ErrorCode errorCode, final String... arguments)'.");

    this.errorCode = errorCode;
    this.arguments.addAll(asList(arguments));
  }

  public ErrorCode getErrorCode(){

    return errorCode;
  }

  public BlueKitMethodException withArguments(final String... arguments){

    this.arguments.addAll(asList(arguments));
    return this;
  }

  public String[] getArguments(){

    return arguments.toArray(new String[arguments.size()]);
  }
}
