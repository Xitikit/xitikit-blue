package org.xitikit.blue.common.errors;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.*;

/**
 * @author J. Keith Hoopes
 */
public abstract class BlueKitHttpException extends RuntimeException{

    private final ErrorCode errorCode;

    private final List<String> arguments = new ArrayList<>();

    protected BlueKitHttpException(final ErrorCode errorCode){

        Verify.notNull(errorCode, "'errorCode' was null in 'BlueKitHttpException::BlueKitHttpException(final ErrorCode errorCode)'.");
        this.errorCode = errorCode;
    }

    protected BlueKitHttpException(final ErrorCode errorCode, final Throwable throwable){

        super(throwable);

        Verify.notNull(errorCode, "'errorCode' was null in 'BlueKitHttpException::BlueKitHttpException(final ErrorCode errorCode, final Throwable t)'.");
        Verify.notNull(errorCode, "'throwable' was null in 'BlueKitHttpException::BlueKitHttpException(final ErrorCode errorCode, final Throwable t)'.");

        this.errorCode = errorCode;
    }

    protected BlueKitHttpException(final ErrorCode errorCode, final String... arguments){

        Verify.notNull(errorCode, "'errorCode' was null in 'BlueKitHttpException::BlueKitHttpException(final ErrorCode errorCode, final String... arguments)'.");
        Verify.notNull(arguments, "'arguments' were null in 'BlueKitHttpException::BlueKitHttpException(final ErrorCode errorCode, final String... arguments)'.");

        this.errorCode = errorCode;
        this.arguments.addAll(asList(arguments));
    }

    public ErrorCode getErrorCode(){

        return errorCode;
    }

    public BlueKitHttpException withArguments(final String... arguments){

        this.arguments.addAll(asList(arguments));
        return this;
    }

    public String[] getArguments(){

        return arguments.toArray(new String[arguments.size()]);
    }
}
