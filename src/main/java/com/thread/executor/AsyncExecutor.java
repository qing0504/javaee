package com.thread.executor;

import java.util.concurrent.*;

/**
 * 统一控制所有异步请求
 * @author wanchongyang
 * @date 2018/4/26 下午10:41
 */
public class AsyncExecutor extends ThreadPoolExecutor{
    private AsyncExecutor() {
        super(Runtime.getRuntime().availableProcessors() * 10,
                500,
                1L,
                TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(1000),
                new CustomThreadFactory("MyAsync"),
                (r, e) -> System.out.println("active count:" + e.getActiveCount()));
    }

    public static AsyncExecutor instance() {
        return AsyncExecutorHolder.INSTANCE;
    }

    private static class AsyncExecutorHolder {
        private static AsyncExecutor INSTANCE = new AsyncExecutor();
    }

}
