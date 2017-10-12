package org.xitikit.blue.common.errors.test;

import org.junit.Test;
import org.xitikit.blue.nommoc.errors.ForbiddenException;
import org.xitikit.blue.nommoc.errors.MessageSourceResolvableException;

import static junit.framework.TestCase.*;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes *
 */
public class ForbiddenExceptionTest{

  /**
   * Basic constructor and null checks
   */
  @Test
  public void verify(){

    ForbiddenException e;

    e = new ForbiddenException();
    assertTrue(e.getArguments() != null);
    assertTrue(e.getArguments().length == 0);
    assertTrue(e.getCodes() != null);
    assertTrue(e.getCodes().length == 1);
    assertTrue("Forbidden".equals(e.getCodes()[0]));

    e = new ForbiddenException(new Throwable("Test"));
    assertTrue(e.getArguments() != null);
    assertTrue(e.getArguments().length == 0);
    assertTrue(e.getCodes() != null);
    assertTrue(e.getCodes().length == 1);
    assertTrue("Forbidden".equals(e.getCodes()[0]));
    assertTrue("Test".equals(e.getCause().getMessage()));

    e = new ForbiddenException("Test Input");
    assertTrue(e.getArguments() != null);
    assertTrue(e.getArguments().length == 1);
    assertTrue("Test Input".equals(e.getArguments()[0]));
    assertTrue(e.getCodes() != null);
    assertTrue(e.getCodes().length == 1);
    assertTrue("Forbidden".equals(e.getCodes()[0]));
    assertTrue(e.getArguments() != null);
  }

  /**
   * Ensures values remain unchanged when added.
   *
   * @throws Exception when it sees yo momma!
   */
  @Test
  public void addCode() throws Exception{

    MessageSourceResolvableException e = new ForbiddenException("test");
    e.addCode("test").addCode("test2");

    boolean found = false;
    for(String code : e.getCodes()){

      if("test".equals(code) || "test2".equals(code)){
        found = true;
        break;
      }
    }
    assertTrue("addCode failed", found);
  }

  /**
   * Ensures values remain unchanged when added.
   *
   * @throws Exception when it sees MY momma ...
   */
  @Test
  public void addCodes() throws Exception{

    MessageSourceResolvableException e = new ForbiddenException("one", new Exception("test"));
    String[] codes = new String[2];
    codes[0] = "zero";
    codes[1] = "one";
    e.addCodes(codes).addCodes(codes);

    boolean foundZero = false;
    boolean foundOne = false;
    for(String code : e.getCodes()){

      if("zero".equals(code)){
        foundZero = true;
      }
      if("one".equals(code)){
        foundOne = true;
      }
    }
    assertTrue(foundZero);
    assertTrue(foundOne);
  }

  /**
   * Ensures values remain unchanged when added.
   *
   * @throws Exception when it sees dead people.
   */
  @Test
  public void addArgument() throws Exception{

    MessageSourceResolvableException e = new ForbiddenException("test", "one", "two");
    e.addArgument("test").addArgument("test2");

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

    MessageSourceResolvableException e = new ForbiddenException();
    Object[] arguments = new Object[2];
    arguments[0] = "zero";
    arguments[1] = "one";
    e.addArguments(arguments).setDefaultMessage("default2");

    boolean foundZero = false;
    boolean foundOne = false;

    for(Object argArray : e.getArguments()){

      Object[] args = (Object[]) argArray;

      for(Object arg : args){
        if("zero".equals(arg)){
          foundZero = true;
        }
        if("one".equals(arg)){
          foundOne = true;
        }
        if(foundZero && foundOne){
          break;
        }
      }
      if(foundZero && foundOne){
        break;
      }
    }
    assertTrue(foundZero);
    assertTrue(foundOne);
  }

  /**
   * Ensures values remain unchanged when added.
   *
   * @throws Exception The exception is a lie...
   */
  @Test
  public void setDefaultMessage() throws Exception{

    MessageSourceResolvableException e = new ForbiddenException();
    e.setDefaultMessage("default");
    assertTrue("default".equals(e.getDefaultMessage()));
  }
}