package org.xitikit.blue.graphapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.xitikit.blue.graphapi.AzureGraphApiClient;
import org.xitikit.blue.graphapi.model.GraphApiUser;
import org.xitikit.blue.graphapi.model.PaginatedUsers;

import java.util.List;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
@Service("graphApiUserService")
public class GraphApiUserService implements IGraphApiUserService{

    private final IAzureCustomAttributeCacheService azureCustomAttributeCacheService;

    private final AzureGraphApiClient azureGraphApiClient;

    @Autowired
    public GraphApiUserService(final IAzureCustomAttributeCacheService azureCustomAttributeCacheService, final AzureGraphApiClient azureGraphApiClient){

        this.azureCustomAttributeCacheService = azureCustomAttributeCacheService;
        this.azureGraphApiClient = azureGraphApiClient;
    }

    @Override
    public List<GraphApiUser> getUnlinkedUserAccounts(){
        //TODO add support for pagination when supported by microsoft
        PaginatedUsers paginatedUsers = azureGraphApiClient.getUsers("");

        return paginatedUsers.getUsers();
    }

    @Override
    public void deleteUser(String uuid){

        azureGraphApiClient.deleteUser(uuid);
    }

    @Override
    public void updateUser(GraphApiUser user){

        azureGraphApiClient.updateUser(user);
    }

    /**
     * Update the azure user.
     *
     * @return The updated user. Null is returned if an attempt is made to override an existing user by
     *     selecting an email address that already belongs to someone else.
     */
    @Override
    public GraphApiUser updateProfile(String uuid, String firstName, String lastName, String email, String emailConfirmation){

        GraphApiUser updatedAzureUser = new GraphApiUser();
        updatedAzureUser.setId(uuid);
        updatedAzureUser.setGivenName(firstName);
        updatedAzureUser.setSurname(lastName);
        updatedAzureUser.setDisplayName(lastName + " " + firstName);

        //Ensure that the email and confirmation match
        email = email == null ? "" : email.trim();
        if(!"".equals(email) && email.equals(StringUtils.trimAllWhitespace(emailConfirmation))){

            //Ensure that they aren't trying to set the email to one that already exists. Only search for
            //existing users if they actually changed the email address.
            GraphApiUser azureUser = getUser(uuid);
            if(!email.equals(azureUser.getSignInEmail())){
                org.xitikit.blue.graphapi.model.PaginatedUsers paginatedUsers = azureGraphApiClient.getUsers("$filter=signInNames/any(x:x/value eq '" + email + "')");
                List<GraphApiUser> users = paginatedUsers.getUsers();
                //If one comes back and it matches the email they are trying to change it to, then they can't use that email.
                if(users != null && users.size() != 0){
                    if(users.size() == 1 && email.equals(users
                        .get(0)
                        .getSignInEmail())){
                        return null;
                    }else{
                        throw new RuntimeException("An unexpected number of users matching the email " + email + " were found. Count: " + users
                            .size());
                    }
                }
            }

            updatedAzureUser.setSignInEmail(email);
        }else{
            throw new RuntimeException("Invalid email address inputs for user update.");
        }

        updateUser(updatedAzureUser);
        return getUser(uuid);
    }

    @Override
    public PaginatedUsers getPaginatedUsers(int pageSize, String skipToken){

        return azureGraphApiClient.getUsers(null, pageSize, skipToken);
    }

    @Override
    public GraphApiUser getUser(String uuid){

        return azureGraphApiClient.getUser(uuid);
    }
}
