package org.xitikit.blue.graphapi.service;

import org.xitikit.blue.graphapi.model.*;
import org.xitikit.blue.nommoc.errors.http.BlueKitHttpException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Copyright Xitikit.org 2017
 *
 * Client which provides CRUD functionality for working with the Azure AD B2C Graph API.
 *
 * @author J. Keith Hoopes
 */
public interface AzureGraphApiClient{

    /**
     * Remove the account matching the given azure uuid
     *
     * @param uuid Should match the id of the user in Azure AD B2C
     */
    void deleteUser(final String uuid) throws BlueKitHttpException;

    /**
     * Retrieves the graph-api access token used for further requests to the graph api.
     * If a token cannot be obtained, this method should throw an appropriate  throws BlueKitHttpException.
     *
     * @return {@code AccessToken} which should never be null.
     */
    @Nullable
    AccessToken getAccessToken() throws BlueKitHttpException;

    /**
     * Saves the current state of the {@link GraphApiUser}.
     * This method shout not allow the creation of new users.
     *
     * @param graphApiUser {@link GraphApiUser}.
     *
     * @throws BlueKitHttpException if there is a problem communicating with the B2C tenant.
     */
    void updateUser(final GraphApiUser graphApiUser) throws BlueKitHttpException;

    /**
     * Retrieves the user matching the given uuid.
     *
     * @param uuid ID in Azure AD B2C to retrieve.
     *
     * @return The matching {@link GraphApiUser}, or null if no user is found.
     *
     * @throws BlueKitHttpException if there is a problem communicating with the B2C tenant.
     */
    @Nullable
    GraphApiUser getUser(final String uuid) throws BlueKitHttpException;

    /**
     * Retrieves users matching the given filter expression.
     *
     * Should never return null, but the size of the users list contained by
     * {@link PaginatedUsers} can be 0.
     *
     * @param filterExpression Used to narrow results.
     * @param pageSize Number of users to return at once. A value less than 1
     *     should return all users matching the expression.
     * @param nextLink This is the value of the "odata.nextLink" value
     *     that is set in the {@link PaginatedUsers}. Used
     *     to retrieve the next page of results. A blank value
     *     should cause the first page of results to be returned.
     *
     * @return The {@link PaginatedUsers} matching the given filter expression.
     *
     * @throws BlueKitHttpException if there is a problem communicating with the B2C tenant.
     */
    @Nonnull
    PaginatedUsers getUsers(String filterExpression, int pageSize, String nextLink);

    /**
     * Find the {@link Applications} model containing all available apps
     * for the configured B2C tenant.
     *
     * Should never return null. If values are not found, then a {@link BlueKitHttpException}
     * should be thrown.
     *
     * @return The {@link Applications} model containing all available apps
     *     for the configured B2C tenant.
     *
     * @throws BlueKitHttpException if no results could be found.
     */
    @Nonnull
    Applications getApplications() throws BlueKitHttpException;

    /**
     * An {@link ExtensionProperty} is how custom attributes in B2C are named.
     *
     * Should never return null. If values are not found, then a {@link BlueKitHttpException}
     * should be thrown.
     *
     * @param extensionAppId {@link String} representing then ID of the "b2c-extensions-app" for the configured tennant.     *
     *
     * @return the {@link ExtensionProperty} matching the extensionAppId
     *
     * @throws BlueKitHttpException if no results could be found.
     */
    @Nonnull
    ExtensionProperties getExtensionProperties(String extensionAppId) throws BlueKitHttpException;
}

