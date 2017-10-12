package org.xitikit.blue.common.errors.test;

import org.junit.Test;
import org.xitikit.blue.nommoc.errors.MessageSourceResolvableException;
import org.xitikit.blue.nommoc.errors.MethodNotAllowedException;

import static junit.framework.TestCase.*;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes *
 */
public class MethodNotAllowedExceptionTest{

  /**
   * Ensure default constructor is still passing arguments
   * like a ... uhm .. i dunno .. something something Trumps a bitch.
   */
  @Test
  public void verify(){

    MethodNotAllowedException e;

    e = new MethodNotAllowedException();
    assertTrue(e.getArguments() != null);
    assertTrue(e.getArguments().length == 0);
    assertTrue(e.getCodes() != null);
    assertTrue(e.getCodes().length == 1);
    assertTrue("MethodNotAllowed".equals(e.getCodes()[0]));

    e = new MethodNotAllowedException(new Throwable("Test"));
    assertTrue(e.getArguments() != null);
    assertTrue(e.getArguments().length == 0);
    assertTrue(e.getCodes() != null);
    assertTrue(e.getCodes().length == 1);
    assertTrue("MethodNotAllowed".equals(e.getCodes()[0]));
    assertTrue("Test".equals(e.getCause().getMessage()));

    e = new MethodNotAllowedException("Test Input");
    assertTrue(e.getArguments() != null);
    assertTrue(e.getArguments().length == 1);
    assertTrue("Test Input".equals(e.getArguments()[0]));
    assertTrue(e.getCodes() != null);
    assertTrue(e.getCodes().length == 1);
    assertTrue("MethodNotAllowed".equals(e.getCodes()[0]));
    assertTrue(e.getArguments() != null);
  }

  /**
   * Ensures values remain unchanged when added.
   *
   * @throws Exception when it sees yo momma!
   */
  @Test
  public void addCode() throws Exception{

    MessageSourceResolvableException e = new MethodNotAllowedException("test");
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

    MessageSourceResolvableException e = new MethodNotAllowedException("one", new Exception("test"));
    String[] codes = new String[2];
    codes[0] = "zero the hero";
    codes[1] = "one";
    e.addCodes(codes).addCodes(codes);

    boolean foundZero = false;
    boolean foundOne = false;
    for(String code : e.getCodes()){

      if("zero the hero".equals(code)){
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

    MessageSourceResolvableException e = new MethodNotAllowedException("test", "one", "two");
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

    MessageSourceResolvableException e = new MethodNotAllowedException();
    Object[] arguments = new Object[2];
    arguments[0] = "zero the hero";
    arguments[1] = "one";
    e.addArguments(arguments).setDefaultMessage("default2");

    boolean foundZero = false;
    boolean foundOne = false;

    for(Object argArray : e.getArguments()){

      Object[] args = (Object[]) argArray;

      for(Object arg : args){
        if("zero the hero".equals(arg)){
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

    MessageSourceResolvableException e = new MethodNotAllowedException();
    e.setDefaultMessage("default");
    assertTrue("default".equals(e.getDefaultMessage()));
  }
}