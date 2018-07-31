package com.thread.executor.support;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @author wanchongyang
 * @date 2018/7/31 下午3:49
 */
public class DefaultThreadPoolTaskExecutor extends AbstractThreadPoolTaskExecutor implements AsyncTaskExecutor {
    public DefaultThreadPoolTaskExecutor() {
        register(this);
    }

    @Override
    public void execute(Runnable task) {
        AsyncExecutor.instance().execute(task);
    }

    @Override
    public Future<?> submit(Runnable task) {
        return AsyncExecutor.instance().submit(task);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return AsyncExecutor.instance().submit(task);

    }

    @Override
    protected String getName() {
        return "taskExecutor";
    }
}
