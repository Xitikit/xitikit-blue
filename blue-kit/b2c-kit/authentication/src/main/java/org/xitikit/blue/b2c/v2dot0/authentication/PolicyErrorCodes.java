package org.xitikit.blue.b2c.v2dot0.authentication;

/**
 * These codes indicate that some action
 * needs to be taken by the application. In
 * most (or really all that we have encountered
 * so far), this is because the user forgot their
 * password and/or username. In that case, they need
 * to be redirected to the password recovery flow in Azure.
 * <p>
 * The purpose of keeping them in all lowercase
 * characters is that Azure returns the String
 * value of the code like this. Making them equal
 * allows for easier JSON parsing.
 * <p>
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
public enum PolicyErrorCodes{

    /**
     * This is the only error code found so far, and it is
     * pretty generic. To determine whether or not this is the
     * result of a "forgot password" request, you need verify
     * that the errorDescription contains the work "forgot".
     */
    access_denied
}
