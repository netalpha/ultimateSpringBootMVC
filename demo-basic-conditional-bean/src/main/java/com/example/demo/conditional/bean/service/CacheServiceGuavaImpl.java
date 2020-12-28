package com.example.demo.conditional.bean.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(prefix = "cache", name = "impl", havingValue = "guava")
public class CacheServiceGuavaImpl implements CacheService {

    private Cache<String, String> guavaCache = CacheBuilder.newBuilder().build();

    @Override
    public void save(String key, String value) {
        guavaCache.put(key, value);
    }
}
