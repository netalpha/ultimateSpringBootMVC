package com.example.demo.conditional.bean.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@ConditionalOnProperty(prefix = "cache", name = "impl", havingValue = "memory")
public class CacheServiceMemoryImpl implements CacheService {

    private Map<String, String> cache = new HashMap<>();

    @Override
    public void save(String key, String value) {
        cache.put(key, value);
    }
}
