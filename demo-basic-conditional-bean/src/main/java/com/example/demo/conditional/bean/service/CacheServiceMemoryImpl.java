package com.example.demo.conditional.bean.service;

import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.EnsuresKeyFor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@ConditionalOnProperty(prefix = "cache", name = "impl", havingValue = "memory")
@Slf4j
public class CacheServiceMemoryImpl implements CacheService {

    private Map<String, String> cache = new HashMap<>();

    @Override
    public void save(String key, String value) {
    	log.info("put " + key + " with value " + value);
        cache.put(key, value);
    }
}
