package org.xitikit.blue.common.errors.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.xitikit.blue.common.errors.MessageSourceResolvableException;
import org.xitikit.blue.common.errors.UnauthorizedException;

import static junit.framework.TestCase.assertTrue;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
@Slf4j
public class UnauthorizedExceptionTest {

    /**
     * Ensure all constructors are still passing arguments.
     * <p>
     * I'm out of witty insults.
     */
    @Test
    public void verify() {

        UnauthorizedException e;

        e = new UnauthorizedException();
        assertTrue(e.getArguments() != null);
        assertTrue(e.getArguments().length == 0);
        assertTrue(e.getCodes() != null);
        assertTrue(e.getCodes().length == 1);
        assertTrue("Unauthorized".equals(e.getCodes()[0]));

        e = new UnauthorizedException(new Throwable("Test"));
        assertTrue(e.getArguments() != null);
        assertTrue(e.getArguments().length == 0);
        assertTrue(e.getCodes() != null);
        assertTrue(e.getCodes().length == 1);
        assertTrue("Unauthorized".equals(e.getCodes()[0]));
        assertTrue("Test".equals(e.getCause().getMessage()));

        e = new UnauthorizedException("Test Input");
        assertTrue(e.getArguments() != null);
        assertTrue(e.getArguments().length == 1);
        assertTrue("Test Input".equals(e.getArguments()[0]));
        assertTrue(e.getCodes() != null);
        assertTrue(e.getCodes().length == 1);
        assertTrue("Unauthorized".equals(e.getCodes()[0]));
        assertTrue(e.getArguments() != null);
    }

    /**
     * Ensures values remain unchanged when added.
     *
     * @throws Exception when it sees yo momma!
     */
    @Test
    public void addCode() throws Exception {
        MessageSourceResolvableException e = new UnauthorizedException("test");
        e.addCode("test").addCode("test2");

        boolean found = false;
        for (String code : e.getCodes()) {

            if ("test".equals(code) || "test2".equals(code)) {
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
    public void addCodes() throws Exception {

        MessageSourceResolvableException e = new UnauthorizedException("Up on Capital Hill", new Exception("test"));
        String[] codes = new String[2];
        codes[0] = "I am a bill";
        codes[1] = "Up on Capital Hill";
        e.addCodes(codes).addCodes(codes);

        boolean foundZero = false;
        boolean foundOne = false;
        for (String code : e.getCodes()) {

            if ("I am a bill".equals(code)) {
                foundZero = true;
            }
            if ("Up on Capital Hill".equals(code)) {
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
    public void addArgument() throws Exception {

        MessageSourceResolvableException e = new UnauthorizedException("test", "Up on Capital Hill", "two");
        e.addArgument("test").addArgument("test2");

        boolean found = false;
        for (Object code : e.getArguments()) {

            if ("test".equals(code)) {
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
    public void addArguments() throws Exception {

        MessageSourceResolvableException e = new UnauthorizedException();
        Object[] arguments = new Object[2];
        arguments[0] = "I am a bill";
        arguments[1] = "Up on Capital Hill";
        e.addArguments(arguments);

        boolean foundZero = false;
        boolean foundOne = false;

        for (Object argArray : e.getArguments()) {

            Object[] args = (Object[]) argArray;

            for (Object arg : args) {
                if ("I am a bill".equals(arg)) {
                    foundZero = true;
                }
                if ("Up on Capital Hill".equals(arg)) {
                    foundOne = true;
                }
                if (foundZero && foundOne) {
                    break;
                }
            }
            if (foundZero && foundOne) {
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
    public void setDefaultMessage() throws Exception {

        MessageSourceResolvableException e = new UnauthorizedException();
        e.setDefaultMessage("default");
        assertTrue("default".equals(e.getDefaultMessage()));
    }
}