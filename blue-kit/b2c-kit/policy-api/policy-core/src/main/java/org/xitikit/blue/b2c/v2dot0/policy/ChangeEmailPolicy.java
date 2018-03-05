package org.xitikit.blue.b2c.v2dot0.policy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;

/**
 * As of the date of creation, Azure AD B2C does not support changing the email
 * through a policy (such as the EditProfilePolicyA). To change a users email,
 * the graph-api module should be used to create a custom programmatic approach
 * to email changes. This class is a placeholder as a reminder of the dream
 * that perchance this BUG will be fixed, and this critical piece of
 * functionality will be implemented.
 * <p>
 * Copyright Xitikit.org ${year}
 *
 * @author J. Keith Hoopes
 */
public class ChangeEmailPolicy implements PolicyForB2C{

    private static final Logger log = LoggerFactory.getLogger(ChangeEmailPolicy.class);

    @Override
    public String getBasePath(){

        if(log.isDebugEnabled()){
            log.debug("Policy Not Supported -> ChangeEmailPolicy::getBasePath was called, but ChangeEmailPolicy is not supported in Azure AD B2C.");
        }
        return null;
    }

    @Override
    public void setBasePath(final String basePath){

        if(log.isDebugEnabled()){
            log.debug("Policy Not Supported -> ChangeEmailPolicy::setBasePath was called, but ChangeEmailPolicy is not supported in Azure AD B2C.");
        }
    }

    @Override
    public String getName(){

        if(log.isDebugEnabled()){
            log.debug("Policy Not Supported -> ChangeEmailPolicy::getName was called, but ChangeEmailPolicy is not supported in Azure AD B2C.");
        }
        return null;
    }

    @Override
    public void setName(final String name){

        if(log.isDebugEnabled()){
            log.debug("Policy Not Supported -> ChangeEmailPolicy::setName was called, but ChangeEmailPolicy is not supported in Azure AD B2C.");
        }
    }

    @Override
    public String getRedirectUrl(){

        if(log.isDebugEnabled()){
            log.debug("Policy Not Supported -> ChangeEmailPolicy::getRedirectUrl was called, but ChangeEmailPolicy is not supported in Azure AD B2C.");
        }
        return null;
    }

    @Override
    public void setRedirectUrl(final String ignored){

        if(log.isDebugEnabled()){
            log.debug("Policy Not Supported -> ChangeEmailPolicy::setRedirectUrl was called, but ChangeEmailPolicy is not supported in Azure AD B2C.");
        }
    }

    @Nullable
    @Override
    public String getTemplateUrl(){

        if(log.isDebugEnabled()){
            log.debug("Policy Not Supported -> ChangeEmailPolicy::getTemplateUrl was called, but ChangeEmailPolicy is not supported in Azure AD B2C.");
        }
        return null;
    }

    @Override
    public void setTemplateUrl(final String ignored){

        if(log.isDebugEnabled()){
            log.debug("Policy Not Supported -> ChangeEmailPolicy::setTemplateUrl was called, but ChangeEmailPolicy is not supported in Azure AD B2C.");
        }
    }

    @Override
    public boolean isDisabled(){

        if(log.isDebugEnabled()){
            log.debug("Policy Not Supported -> ChangeEmailPolicy::isDisabled was called, but ChangeEmailPolicy is not supported in Azure AD B2C.");
        }
        return true;
    }

    @Override
    public void setDisabled(final boolean ignored){

        if(log.isDebugEnabled()){
            log.debug("Policy Not Supported -> ChangeEmailPolicy::setDisabled was called, but ChangeEmailPolicy is not supported in Azure AD B2C.");
        }
    }
}
