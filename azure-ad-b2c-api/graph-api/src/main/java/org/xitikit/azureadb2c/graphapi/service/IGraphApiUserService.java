package org.xitikit.blue.authorization.azure.ad.b2c.graphapi.service;

import org.xitikit.blue.authorization.azure.ad.b2c.graphapi.model.GraphApiUser;
import org.xitikit.blue.authorization.azure.ad.b2c.graphapi.model.PaginatedUsers;

import java.time.LocalDate;
import java.util.List;

/**
 * @author J. Keith Hoopes
 *         Copyright Xitikit.org 2017
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface IGraphApiUserService {

    String LINKED_TRUE = "true";

    String LINKED_FALSE = "false";

    String SIGNUP_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss:SSS";

    List<GraphApiUser> getUnlinkedUserAccounts();

    void deleteUser(String uuid);

    void updateUser(GraphApiUser user);

    GraphApiUser updateProfile(String uuid, String firstName, String lastName, String email, String emailConfirmation);

    void updateUserAsNewSignup(String uuid);

    void updateUserAsLinked(String uuid, LocalDate signupDate);

    void updateUserAsUnlinked(String uuid, LocalDate customAttributeOne);

    PaginatedUsers getPaginatedUsers(int pageSize, String skipToken);

    GraphApiUser getUser(String uuid);
}
