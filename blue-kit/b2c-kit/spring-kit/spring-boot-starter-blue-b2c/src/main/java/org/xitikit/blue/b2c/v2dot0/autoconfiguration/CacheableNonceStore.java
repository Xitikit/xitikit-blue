package org.xitikit.blue.b2c.v2dot0.autoconfiguration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.xitikit.blue.common.services.nonce.Nonce;
import org.xitikit.blue.common.services.nonce.NonceStore;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@CacheConfig
@ConditionalOnMissingBean(value = NonceStore.class)
@ConditionalOnBean(annotation = EnableCaching.class)
@ConditionalOnProperty(prefix = "blue-kit.b2c.nonce", name = "disabled", havingValue = "false")
public class CacheableNonceStore implements NonceStore{

    private static final Map<String, Nonce> store = new ConcurrentHashMap<>();

    @Override
    @CachePut(
        cacheNames = "blue-kit.b2c.nonce.cache",
        key = "#nonce.value",
        value = "#nonce")
    public void put(@Nonnull final Nonce nonce){

        store.put(nonce.getValue(), nonce);
    }

    @Nullable
    @Override
    @CacheEvict(
        cacheNames = "blue-kit.b2c.nonce.cache",
        key = "#nonceValue")
    public Nonce get(final String nonceValue){

        return store.get(nonceValue);
    }

    @Override
    @CacheEvict(
        cacheNames = "blue-kit.b2c.nonce.cache",
        allEntries = true
    )
    public void purge(){

        store.clear();
    }
}
