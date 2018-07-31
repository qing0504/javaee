package com.thread.executor.support;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author wanchongyang
 * @date 2018/7/31 下午5:45
 */
public class AsyncResult<V> implements Future<V> {

    private final V value;

    private final ExecutionException executionException;


    /**
     * Create a new AsyncResult holder.
     *
     * @param value the value to pass through
     */
    public AsyncResult(V value) {
        this(value, null);
    }

    /**
     * Create a new AsyncResult holder.
     *
     * @param value the value to pass through
     */
    private AsyncResult(V value, ExecutionException ex) {
        this.value = value;
        this.executionException = ex;
    }


    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return true;
    }

    @Override
    public V get() throws ExecutionException {
        if (this.executionException != null) {
            throw this.executionException;
        }
        return this.value;
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws ExecutionException {
        return get();
    }

}
