package org.xitikit.blue.graphapi.service;

import java.util.List;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes *
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface IGraphApiUserService{

    List<org.xitikit.blue.graphapi.model.GraphApiUser> getUnlinkedUserAccounts();

    void deleteUser(String uuid);

    void updateUser(org.xitikit.blue.graphapi.model.GraphApiUser user);

    org.xitikit.blue.graphapi.model.GraphApiUser updateProfile(String uuid, String firstName, String lastName, String email, String emailConfirmation);

    org.xitikit.blue.graphapi.model.PaginatedUsers getPaginatedUsers(int pageSize, String skipToken);

    org.xitikit.blue.graphapi.model.GraphApiUser getUser(String uuid);
}
