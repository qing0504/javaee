package com.thread.executor.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wanchongyang
 * @date 2018/7/31 下午4:22
 */
public abstract class AbstractThreadPoolTaskExecutor {
    protected static final Map<String, AsyncTaskExecutor> THREAD_POOL_TASK_EXECUTOR_MAP = new ConcurrentHashMap<>(16);

    protected abstract String getName();

    protected void register(AsyncTaskExecutor taskExecutor) {
        THREAD_POOL_TASK_EXECUTOR_MAP.putIfAbsent(getName(), taskExecutor);
    }

    protected AsyncTaskExecutor getInstance(String key) {
        return THREAD_POOL_TASK_EXECUTOR_MAP.get(key);
    }
}
