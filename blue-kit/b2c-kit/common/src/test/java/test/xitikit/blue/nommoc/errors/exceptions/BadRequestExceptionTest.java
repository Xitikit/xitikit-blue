package test.xitikit.blue.nommoc.errors.exceptions;

import org.junit.jupiter.api.Test;
import org.xitikit.blue.nommoc.errors.http.BlueKitHttpException;
import org.xitikit.blue.nommoc.errors.http.exceptions.BadRequestException;

import static org.junit.jupiter.api.Assertions.*;
import static org.xitikit.blue.nommoc.errors.http.ErrorCode.*;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes *
 */
class BadRequestExceptionTest{

  /**
   * Basic constructor and null checks
   */
  @Test
  void verify(){

    BadRequestException e;

    e = new BadRequestException();
    assertTrue(e.getArguments() != null);
    assertTrue(e.getArguments().length == 0);
    assertTrue(e.getErrorCode() == BAD_REQUEST);

    e = new BadRequestException(new Throwable("Test"));
    assertTrue(e.getArguments() != null);
    assertTrue(e.getArguments().length == 0);
    assertTrue(e.getErrorCode() == BAD_REQUEST);
    assertTrue("Test".equals(e.getCause().getMessage()));

    e = new BadRequestException("Test Input");
    assertTrue(e.getArguments() != null);
    assertTrue(e.getArguments().length == 1);
    assertTrue(e.getErrorCode() == BAD_REQUEST);
    assertTrue("Test Input".equals(e.getArguments()[0]));
  }

  /**
   * Ensures values remain unchanged when added.
   *
   * @throws BadRequestException when it sees dead people.
   */
  @Test
  void addArgument(){

    BlueKitHttpException e = new BadRequestException("test", "one", "two");
    e.withArguments("test").withArguments("test2");

    boolean found = false;
    for(Object code : e.getArguments()){

      if("test".equals(code)){
        found = true;
        break;
      }
    }
    assertTrue(found);
  }

  /**
   * Ensures values remain unchanged when added.
   *
   * @throws BadRequestException when it sees Bruce Willis.
   */
  @Test
  void addArguments(){

    BlueKitHttpException e = new BadRequestException();
    String[] arguments = new String[2];
    arguments[0] = "all";
    arguments[1] = "might";
    e.withArguments(arguments);

    boolean foundZero = false;
    boolean foundOne = false;

    for(String arg : e.getArguments()){

      if("all".equals(arg)){
        foundZero = true;
      }
      if("might".equals(arg)){
        foundOne = true;
      }
      if(foundZero && foundOne){
        break;
      }
    }
    assertTrue(foundZero);
    assertTrue(foundOne);
  }
}