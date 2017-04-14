package org.xitikit.blue.authorization.azure.ad.b2c.openidconnect.errors;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * @author J. Keith Hoopes
 *         Copyright Xitikit.org 2017
 */
public class MessageSourceResolvableExceptionTest {

    @SuppressWarnings("ThrowableInstanceNeverThrown")
    private MessageSourceResolvableException e = new MessageSourceResolvableException();

    @Test
    public void addCode() throws Exception {

        e.addCode("test");

        boolean found = false;
        for (String code : e.getCodes()) {

            if ("test".equals(code)) {
                found = true;
                break;
            }
        }
        assertTrue("addCode failed", found);
    }

    @Test
    public void addCodes() throws Exception {

        String[] codes = new String[2];
        codes[0] = "zero";
        codes[1] = "one";
        e.addCodes(codes);

        boolean foundZero = false;
        boolean foundOne = false;
        for (String code : e.getCodes()) {

            if ("zero".equals(code)) {
                foundZero = true;
            }
            if ("one".equals(code)) {
                foundOne = true;
            }
        }
        assertTrue(foundZero);
        assertTrue(foundOne);
    }

    @Test
    public void addArgument() throws Exception {

        e.addArgument("test");

        boolean found = false;
        for (Object code : e.getArguments()) {

            if ("test".equals(code)) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    public void addArguments() throws Exception {

        Object[] arguments = new Object[2];
        arguments[0] = "zero";
        arguments[1] = "one";
        e.addArguments(arguments);

        boolean foundZero = false;
        boolean foundOne = false;

        for (Object argArray : e.getArguments()) {

            Object[] args = (Object[]) argArray;

            for (Object arg : args) {
                if ("zero".equals(arg)) {
                    foundZero = true;
                }
                if ("one".equals(arg)) {
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

    @Test
    public void setDefaultMessage() throws Exception {

        e.setDefaultMessage("default");
        assertTrue("default".equals(e.getDefaultMessage()));
    }
}