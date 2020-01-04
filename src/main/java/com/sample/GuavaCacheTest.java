package com.sample;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author wanchongyang
 * @date 2019/12/3 3:17 下午
 */
public class GuavaCacheTest {
    private static Cache<String, Object> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .maximumSize(1_000)
            .concurrencyLevel(16)
            .initialCapacity(16)
            .build();

    public static void main(String[] args) {
        List<String> list = List.of("aa", "bb", "cc", "dd", "ee", "ff", "gg");
    }
}
