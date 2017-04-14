package org.xitikit.blue.authorization.azure.ad.b2c.graphapi.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xitikit.blue.authorization.azure.ad.b2c.graphapi.AzureGraphApiClient;
import org.xitikit.blue.authorization.azure.ad.b2c.graphapi.model.GraphApiUser;
import org.xitikit.blue.authorization.azure.ad.b2c.graphapi.model.PaginatedUsers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author J. Keith Hoopes
 *         Copyright Xitikit.org 2017
 */
@Service("graphApiUserService")
public class GraphApiUserService implements IGraphApiUserService {

    @Autowired
    private AzureCustomAttributeCacheService azureCustomAttributeCacheService;

    @Autowired
    private AzureGraphApiClient azureGraphApiClient;

    @Override
    public List<GraphApiUser> getUnlinkedUserAccounts() {
        //TODO add support for pagination when supported by microsoft
        PaginatedUsers paginatedUsers = azureGraphApiClient.getUsers(
                "$filter=" + azureCustomAttributeCacheService.getLinkedAttributeName() + " eq '" + LINKED_FALSE + "'");

        return paginatedUsers.getUsers();
    }

    @Override
    public void deleteUser(String uuid) {

        azureGraphApiClient.deleteUser(uuid);
    }

    @Override
    public void updateUser(GraphApiUser user) {

        azureGraphApiClient.updateUser(user);
    }

    /**
     * Update the azure user.
     *
     * @return The updated user. Null is returned if an attempt is made to override an existing user by
     * selecting an email address that already belongs to someone else.
     */
    @Override
    public GraphApiUser updateProfile(String uuid, String firstName, String lastName, String email, String emailConfirmation) {

        GraphApiUser updatedAzureUser = new GraphApiUser();
        updatedAzureUser.setId(uuid);
        updatedAzureUser.setGivenName(firstName);
        updatedAzureUser.setSurname(lastName);
        updatedAzureUser.setDisplayName(lastName + " " + firstName);

        //Ensure that the email and confirmation match
        email = StringUtils.trimToEmpty(email);
        if (!"".equals(email) && email.equals(StringUtils.trimToEmpty(emailConfirmation))) {

            //Ensure that they aren't trying to set the email to one that already exists. Only search for
            //existing users if they actually changed the email address.
            GraphApiUser azureUser = getUser(uuid);
            if (!email.equals(azureUser.getSignInEmail())) {
                PaginatedUsers paginatedUsers = azureGraphApiClient.getUsers("$filter=signInNames/any(x:x/value eq '" + email + "')");
                List<GraphApiUser> users = paginatedUsers.getUsers();
                //If one comes back and it matches the email they are trying to change it to, then they can't use that email.
                if (users != null && users.size() != 0) {
                    if (users.size() == 1 && email.equals(users.get(0).getSignInEmail())) {
                        return null;
                    } else {
                        throw new RuntimeException("An unexpected number of users matching the email " + email + " were found. Count: " + users.size());
                    }
                }
            }

            updatedAzureUser.setSignInEmail(email);
        } else {
            throw new RuntimeException("Invalid email address inputs for user update.");
        }

        updateUser(updatedAzureUser);
        return getUser(uuid);
    }

    @Override
    public void updateUserAsNewSignup(String uuid) {

        GraphApiUser azureUser = new GraphApiUser();
        azureUser.setId(uuid);
        azureUser.setLinked(LINKED_FALSE);
        LocalDateTime todayMidnight = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT);
        azureUser.setCustomAttributeOne(todayMidnight.format(DateTimeFormatter.ofPattern(SIGNUP_DATE_TIME_PATTERN)));
        azureGraphApiClient.updateUser(azureUser);
    }

    @Override
    public void updateUserAsLinked(String uuid, LocalDate signupDate) {

        updatedLinkedStatus(uuid, signupDate, LINKED_TRUE);
    }

    @Override
    public void updateUserAsUnlinked(String uuid, LocalDate signupDate) {

        updatedLinkedStatus(uuid, signupDate, LINKED_FALSE);
    }

    @Override
    public PaginatedUsers getPaginatedUsers(int pageSize, String skipToken) {

        return azureGraphApiClient.getUsers(null, pageSize, skipToken);
    }

    @Override
    public GraphApiUser getUser(String uuid) {

        return azureGraphApiClient.getUser(uuid);
    }

    private void updatedLinkedStatus(String uuid, LocalDate signupDate, String linkedStatusFlag) {

        GraphApiUser azureUser = new GraphApiUser();
        azureUser.setId(uuid);
        azureUser.setLinked(linkedStatusFlag);
        if (signupDate != null) {
            LocalDateTime todayMidnight = LocalDateTime.of(signupDate, LocalTime.MIDNIGHT);
            azureUser.setCustomAttributeOne(todayMidnight.format(DateTimeFormatter.ofPattern(SIGNUP_DATE_TIME_PATTERN)));
        }

        azureGraphApiClient.updateUser(azureUser);
    }
}
