package org.xitikit.blue.kit.authentication.v2dot0;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xitikit.blue.api.b2c.v2dot0.configuration.NonceProperties;
import org.xitikit.blue.kit.authentication.v2dot0.interfaces.NonceService;
import org.xitikit.blue.kit.authentication.v2dot0.interfaces.NonceStore;

import javax.annotation.Nonnull;

/**
 * This is a simple nonce service using UUIDs and an in-memory thread safe cache.
 *
 * Be aware the use of this class is greedy and will not share it's nonce's with other instances of
 * your application. You will require sticky sessions to be enabled if a load balancer is used
 * to balance between multiple instances of this application. I recommend NOT using this implementation in
 * production.
 *
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 * @see NonceService
 */
@Slf4j
@Service
public final class SimpleNonceService implements NonceService{

    private final NonceStore nonceStore;

    private final NonceProperties nonceProperties;

    @Autowired
    public SimpleNonceService(
      @Nonnull final NonceStore nonceStore,
      @Nonnull final NonceProperties nonceProperties){

        this.nonceStore = nonceStore;
        this.nonceProperties = nonceProperties;
    }

    @Override
    public Nonce generate(){

        Nonce nonce = new Nonce();
        nonceStore.put(nonce);

        return nonce;
    }

    @Override
    public boolean isValid(@Nonnull final String nonceValue){

        Integer timeout = nonceProperties.getTimeout();
        if(nonceProperties.isDisabled() || nonceProperties.getTimeout() == null || nonceProperties.getTimeout() < 1){
            // Nonce services are considered to be disabled if the user explicitly disables them
            // or they have not set a positive timeout value. If the nonce services are disabled,
            // then we do not care what the value is; all values are considered valid.
            return true;
        }
        Nonce nonce = nonceStore.get(nonceValue);
        if(nonce == null){
            return false;
        }
        long now = System.currentTimeMillis();
        long diff = now - nonce.getSystemTimeAtCreation();
        return diff > timeout * 1000;
    }

    @Override
    public boolean isDisabled(){

        return nonceProperties.isDisabled();
    }
}
