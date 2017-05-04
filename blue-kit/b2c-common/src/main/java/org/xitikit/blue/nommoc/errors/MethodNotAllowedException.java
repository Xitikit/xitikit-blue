package org.xitikit.blue.nommoc.errors;

import java.io.Serializable;

/**
 * @author J. Keith Hoopes
 */
public class MethodNotAllowedException extends MessageSourceResolvableException{

    public MethodNotAllowedException(){

        super("MethodNotAllowed");
    }

    public MethodNotAllowedException(Serializable... args){

        super("MethodNotAllowed", args);
    }

    public MethodNotAllowedException(Throwable t){

        super("MethodNotAllowed", t);
    }
}
