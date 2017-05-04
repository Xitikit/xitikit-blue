package org.xitikit.blue.openidconnect;

import lombok.extern.slf4j.Slf4j;
import org.xitikit.blue.config.NonceProperties;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

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
public class GreedyNonceService implements NonceService {

    private NonceProperties nonceProperties;
    private Map<UUID, Long> nonceStore = new ConcurrentHashMap<>();

    public GreedyNonceService(NonceProperties nonceProperties) {
        this.nonceProperties = nonceProperties;
    }

    @Override
    public String generate() {
        UUID nonce = UUID.randomUUID();
        nonceStore.put(nonce, System.currentTimeMillis());
        return nonce.toString();
    }

    @Override
    public boolean isValid(String nonce) {
        try {
            UUID uuid = UUID.fromString(nonce);
            Long val = nonceStore.remove(uuid);
            return val != null && (
                    (System.currentTimeMillis() - val) > nonceProperties.getTimeout() * 1000);
        }
        catch (IllegalArgumentException x) {

            log.warn(x.getMessage(), x);
            return false; // invalid nonce
        }
    }
}
