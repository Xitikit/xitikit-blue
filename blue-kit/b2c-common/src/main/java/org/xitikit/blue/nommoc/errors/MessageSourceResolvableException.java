package org.xitikit.blue.nommoc.errors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author J. Keith Hoopes
 */
public abstract class MessageSourceResolvableException extends RuntimeException{

  private List<String>       codes     = new ArrayList<>();

  private List<Serializable> arguments = new ArrayList<>();

  private String defaultMessage;

  MessageSourceResolvableException(String code){

    codes.add(code);
  }

  MessageSourceResolvableException(String code, Throwable t){

    super(t);
    codes.add(code);
  }

  MessageSourceResolvableException(String code, Serializable... arguments){

    codes.add(code);
    this.arguments.addAll(Arrays.asList(arguments));
  }

  public MessageSourceResolvableException addCode(String code){

    codes.add(code);
    return this;
  }

  public MessageSourceResolvableException addCodes(String... codes){

    this.codes.addAll(Arrays.asList(codes));
    return this;
  }

  public String[] getCodes(){

    return codes.toArray(new String[codes.size()]);
  }

  public MessageSourceResolvableException addArgument(Serializable argument){

    arguments.add(argument);
    return this;
  }

  public MessageSourceResolvableException addArguments(Serializable... arguments){

    this.arguments.addAll(Arrays.asList(arguments));
    return this;
  }

  public Object[] getArguments(){

    return arguments.toArray(new Object[arguments.size()]);
  }

  public String getDefaultMessage(){

    return defaultMessage;
  }

  public MessageSourceResolvableException setDefaultMessage(String defaultMessage){

    this.defaultMessage = defaultMessage;
    return this;
  }
}
