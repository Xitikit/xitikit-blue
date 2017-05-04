package org.xitikit.blue.noitacitnehtua;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.*;

/**
 * Provides greedy in memory storage of nonce values while
 * supporting the "full concurrency of retrievals and high expected
 * concurrency for updates" using a ConcurrentHashMap. This does
 * NOT provide validation.
 * <p>
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
public class GreedyNonceStore implements NonceStore{

    private final Map<String, Nonce> nonceStore = new ConcurrentHashMap<>();

    private long timeout;

    /**
     * @param timeout Time in seconds for which a nonce is valid.
     */
    public GreedyNonceStore(int timeout){

        setTimeout(timeout);
    }

    @Override
    public void put(@Nonnull Nonce nonce){

        nonceStore.put(nonce.getValue(), nonce);
    }

    @Override
    public Nonce get(@Nonnull String nonceValue){

        return nonceStore.get(nonceValue);
    }

    @Override
    public void purge(){

        if(timeout > 0){
            long systemTime = System.currentTimeMillis();
            nonceStore.values().parallelStream()
                .filter(n -> (systemTime - n.getSystemTimeAtCreation()) > timeout)
                .collect(toSet())
                .forEach(n -> nonceStore.remove(n.getValue()));
        }
    }

    /**
     * @return time in seconds that the nonces are valid
     */
    public int getTimeout(){

        return (int) (timeout / 1000);
    }

    /**
     * Sets the value of the timeout in seconds. If the given
     * value is less that 1, then the time is set to 0. For values
     * greater than zero, the timeout is converted to a milliseconds
     * and stored as a long.
     *
     * @param timeout how long the Nonce values are valid in seconds
     */
    public void setTimeout(int timeout){

        this.timeout = timeout > 0 ? (((long) timeout) * 1000L) : 0;
    }
}
