package com.sample;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.google.common.testing.FakeTicker;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 本地缓存Caffeine
 *
 * @author wanchongyang
 * @date 2018/6/20 下午5:16
 */
public class CaffeineTest {
    private static FakeTicker ticker = new FakeTicker();
    /**
     * 手动加载
     */
    private static Cache<String, String> manualCache = Caffeine.newBuilder()
            .expireAfterWrite(5L, TimeUnit.MINUTES)
            .maximumSize(1_000)
            .ticker(ticker::read)
            .build();

    public static void main(String[] args) throws InterruptedException {
        String key = "name";
        // NullPointerException
        // cache.put("name", null);
        System.out.println(manualCache.get(key, k -> {
            return "martin";
        }));
        // 失效缓存
        ticker.advance(5L, TimeUnit.MINUTES);
        System.out.println(manualCache.getIfPresent(key));

        // caffeine的load put 和invalidate操作都是原子的
        AtomicInteger atomicInteger = new AtomicInteger();
        // 同步加载
        LoadingCache<String, String> loadingCache = Caffeine.newBuilder()
                .maximumSize(3)
                .build(k -> {
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(1000);
                    return atomicInteger.incrementAndGet() + "";
                });
        System.out.println("============get");
        loadingCache.get("test");
        loadingCache.invalidate("test");

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "_1");
            loadingCache.get("test");
        }).start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "_2");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long start = System.currentTimeMillis();
            loadingCache.invalidate("test");
            System.out.println("use ms:" + (System.currentTimeMillis() - start));
        }).start();


        Thread.sleep(1200);
        System.out.println("========" + loadingCache.asMap());
        System.out.println("========" + loadingCache.get("test"));
    }
}
