package com.limiter;

import com.google.common.util.concurrent.RateLimiter;
import com.thread.executor.CustomThreadFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * RateLimiter限流
 * @author wanchongyang
 * @date 2018/7/23 下午4:16
 */
public class RateLimiterTest {
    private static final int THREAD_NUM = 100;
    private static RateLimiter rateLimiter = RateLimiter.create(10);

    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
            100,
            100,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(),
            new CustomThreadFactory("ratelimiter")
    );

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_NUM; i++) {
            THREAD_POOL_EXECUTOR.submit(() -> {
                // rateLimiter.acquire()阻塞
                // System.out.println("wait time(s) :" + rateLimiter.acquire());
                boolean acquireFlag = rateLimiter.tryAcquire(100L, TimeUnit.MILLISECONDS);
                if (acquireFlag) {
                    System.out.println(Thread.currentThread().getName() + "========" + atomicInteger.getAndIncrement());
                } else {
                    System.out.println(Thread.currentThread().getName() + " not acquire access.");
                }
            });
        }

        while(true){
            if (THREAD_POOL_EXECUTOR.getActiveCount() == 0) {
                System.out.println("all thread execute ended.");
                THREAD_POOL_EXECUTOR.shutdownNow();
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
