package com.thread.executor.support;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/**
 * @author wanchongyang
 * @date 2018/7/31 下午3:45
 */
public interface AsyncTaskExecutor extends Executor {
    @Override
    void execute(Runnable task);

    Future<?> submit(Runnable task);

    <T> Future<T> submit(Callable<T> task);
}
