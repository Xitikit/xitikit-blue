package org.xitikit.blue.nommoc.errors;

/**
 * @author J. Keith Hoopes
 */
public class ValidationFailedException extends RuntimeException{

    public ValidationFailedException(){

        super("ValidationFailed");
    }

    public ValidationFailedException(String message){

        super(message);
    }

    public ValidationFailedException(String message, Throwable t){

        super(message, t);
    }
}
