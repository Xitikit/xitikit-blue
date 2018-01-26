package test.xitikit.blue.nommoc.errors.exceptions;

import org.junit.Test;
import org.xitikit.blue.nommoc.errors.http.BlueKitHttpException;
import org.xitikit.blue.nommoc.errors.http.exceptions.UnauthorizedException;

import static junit.framework.TestCase.*;
import static org.xitikit.blue.nommoc.errors.http.ErrorCode.*;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes *
 */
public class UnauthorizedExceptionTest{

  /**
   * Basic constructor and null checks
   */
  @Test
  public void verify(){

    UnauthorizedException e;

    e = new UnauthorizedException();
    assertTrue(e.getArguments() != null);
    assertTrue(e.getArguments().length == 0);
    assertTrue(e.getErrorCode() == UNAUTHORIZED);

    e = new UnauthorizedException(new Throwable("Test"));
    assertTrue(e.getArguments() != null);
    assertTrue(e.getArguments().length == 0);
    assertTrue(e.getErrorCode() == UNAUTHORIZED);
    assertTrue("Test".equals(e.getCause().getMessage()));

    e = new UnauthorizedException("Test Input");
    assertTrue(e.getArguments() != null);
    assertTrue(e.getArguments().length == 1);
    assertTrue(e.getErrorCode() == UNAUTHORIZED);
    assertTrue("Test Input".equals(e.getArguments()[0]));
  }

  /**
   * Ensures values remain unchanged when added.
   *
   * @throws Exception when it sees dead people.
   */
  @Test
  public void addArgument() throws Exception{

    BlueKitHttpException e = new UnauthorizedException("test", "one", "two");
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
   * @throws Exception when it sees Bruce Willis.
   */
  @Test
  public void addArguments() throws Exception{

    BlueKitHttpException e = new UnauthorizedException();
    String[] arguments = new String[2];
    arguments[0] = "c";
    arguments[1] = "d";
    e.withArguments(arguments);

    boolean foundZero = false;
    boolean foundOne = false;

    for(String arg : e.getArguments()){

      if("c".equals(arg)){
        foundZero = true;
      }
      if("d".equals(arg)){
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