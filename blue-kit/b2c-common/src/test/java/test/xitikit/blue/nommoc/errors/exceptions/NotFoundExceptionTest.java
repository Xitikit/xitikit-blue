package test.xitikit.blue.nommoc.errors.exceptions;

import org.junit.Test;
import org.xitikit.blue.nommoc.errors.exceptions.NotFoundException;
import org.xitikit.blue.nommoc.errors.BlueKitMethodException;

import static junit.framework.TestCase.*;
import static org.xitikit.blue.nommoc.errors.ErrorCode.NOT_FOUND;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes *
 */
public class NotFoundExceptionTest{

  /**
   * Basic constructor and null checks
   */
  @Test
  public void verify(){

    NotFoundException e;

    e = new NotFoundException();
    assertTrue(e.getArguments() != null);
    assertTrue(e.getArguments().length == 0);
    assertTrue(e.getErrorCode() == NOT_FOUND);

    e = new NotFoundException(new Throwable("Test"));
    assertTrue(e.getArguments() != null);
    assertTrue(e.getArguments().length == 0);
    assertTrue(e.getErrorCode() == NOT_FOUND);
    assertTrue("Test".equals(e.getCause().getMessage()));

    e = new NotFoundException("Test Input");
    assertTrue(e.getArguments() != null);
    assertTrue(e.getArguments().length == 1);
    assertTrue(e.getErrorCode() == NOT_FOUND);
    assertTrue("Test Input".equals(e.getArguments()[0]));
  }

  /**
   * Ensures values remain unchanged when added.
   *
   * @throws Exception when it sees dead people.
   */
  @Test
  public void addArgument() throws Exception{

    BlueKitMethodException e = new NotFoundException("test", "one", "two");
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

    BlueKitMethodException e = new NotFoundException();
    String[] arguments = new String[2];
    arguments[0] = "e";
    arguments[1] = "f";
    e.withArguments(arguments);

    boolean foundZero = false;
    boolean foundOne = false;

    for(String arg : e.getArguments()){


      if("e".equals(arg)){
        foundZero = true;
      }
      if("f".equals(arg)){
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