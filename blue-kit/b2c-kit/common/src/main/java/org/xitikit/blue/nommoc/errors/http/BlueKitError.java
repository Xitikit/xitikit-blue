package org.xitikit.blue.nommoc.errors.http;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.*;

/**
 * A simple object representing API errors.
 * This class is used to allow customization of
 * exception handling while hiding internal details.
 *
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
public class BlueKitError{

  private int code;

  private String name;

  private String description;

  private List<String> arguments = new ArrayList<>(0);

  public BlueKitError(){

    this(
      ErrorCode.INTERNAL_SERVER_ERROR.value(),
      ErrorCode.INTERNAL_SERVER_ERROR.name(),
      ErrorCode.INTERNAL_SERVER_ERROR.getDescription(),
      new ArrayList<>(0));
  }

  public BlueKitError(
    final int code,
    final String name,
    final String description,
    final List<String> arguments){

    setCode(code);
    setName(name);
    setDescription(description);
    setArguments(arguments);
  }

  public BlueKitError(final BlueKitHttpException e){

    this(
      e.getErrorCode().value(),
      e.getErrorCode().name(),
      e.getErrorCode().getDescription(),
      asList(e.getArguments()));
  }

  public int getCode(){

    return code;
  }

  public void setCode(final int code){

    Verify.state(code > 0, "'code' must be greater than 0.");
    this.code = code;
  }

  public String getName(){

    return name;
  }

  public void setName(final String name){

    Verify.notNull(name, "'name' cannot be null.");
    this.name = name;
  }

  public String getDescription(){

    return description;
  }

  public void setDescription(final String description){

    Verify.notNull(description, "'description' cannot be null.");
    this.description = description;
  }

  public List<String> getArguments(){

    return arguments;
  }

  public void setArguments(final List<String> arguments){

    Verify.notNull(arguments, "'arguments' cannot be set to null.");
    this.arguments = Collections.unmodifiableList(arguments);
  }
}
