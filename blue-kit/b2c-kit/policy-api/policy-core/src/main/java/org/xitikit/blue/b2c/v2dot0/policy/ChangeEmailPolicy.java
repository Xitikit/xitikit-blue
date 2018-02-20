package org.xitikit.blue.b2c.v2dot0.policy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * There is no functionality currently implemented to allow a user to
 * change their email address nor username in Azure AD B2C. This class
 * is a placeholder as a reminder of the dream that perchance they will
 * fix this BUG, and implement this critical piece of functionality.
 *
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
public class ChangeEmailPolicy implements PolicyForB2C{

    private static final Logger log = LoggerFactory.getLogger(ChangeEmailPolicy.class);

    @Override
    public String getName(){

        if(log.isInfoEnabled()){
            log.info("Policy Not Supported -> ChangeEmailPolicy::getName was called, but ChangeEmailPolicy is not supported in Azure AD B2C.");
        }
        return "";
    }

    @Override
    public void setName(final String name){

        if(log.isInfoEnabled()){
            log.info("Policy Not Supported -> ChangeEmailPolicy::setName was called, but ChangeEmailPolicy is not supported in Azure AD B2C.");
        }
    }

    @Override
    public String getRedirectUrl(){

        if(log.isInfoEnabled()){
            log.info("Policy Not Supported -> ChangeEmailPolicy::getRedirectUrl was called, but ChangeEmailPolicy is not supported in Azure AD B2C.");
        }
        return "";
    }

    @Override
    public void setRedirectUrl(final String ignored){

        if(log.isInfoEnabled()){
            log.info("Policy Not Supported -> ChangeEmailPolicy::setRedirectUrl was called, but ChangeEmailPolicy is not supported in Azure AD B2C.");
        }
    }

    @Override
    public String getTemplateUrl(){

        if(log.isInfoEnabled()){
            log.info("Policy Not Supported -> ChangeEmailPolicy::getTemplateUrl was called, but ChangeEmailPolicy is not supported in Azure AD B2C.");
        }
        return "";
    }

    @Override
    public void setTemplateUrl(final String ignored){

        if(log.isInfoEnabled()){
            log.info("Policy Not Supported -> ChangeEmailPolicy::setTemplateUrl was called, but ChangeEmailPolicy is not supported in Azure AD B2C.");
        }
    }

    @Override
    public boolean isDisabled(){

        if(log.isInfoEnabled()){
            log.info("Policy Not Supported -> ChangeEmailPolicy::isDisabled was called, but ChangeEmailPolicy is not supported in Azure AD B2C.");
        }
        return true;
    }

    @Override
    public void setDisabled(final boolean ignored){

        if(log.isInfoEnabled()){
            log.info("Policy Not Supported -> ChangeEmailPolicy::setDisabled was called, but ChangeEmailPolicy is not supported in Azure AD B2C.");
        }
    }
}
