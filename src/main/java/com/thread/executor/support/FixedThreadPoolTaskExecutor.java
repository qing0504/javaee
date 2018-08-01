package com.thread.executor.support;

import com.ioc.support.Component;

import java.util.concurrent.*;

/**
 * @author wanchongyang
 * @date 2018/7/31 下午4:06
 */
@Component
public class FixedThreadPoolTaskExecutor extends AbstractThreadPoolTaskExecutor implements AsyncTaskExecutor {
    private static final int THREADS_COUNT = 10;
    private static ThreadPoolExecutor fixedThreadPoolExecutor = new ThreadPoolExecutor(
            THREADS_COUNT,
            THREADS_COUNT,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(),
            new CustomThreadFactory("fixed"),
            (r, e) -> System.out.println("active count:" + e.getActiveCount() + "Executor:" + e.toString())
    );

    public FixedThreadPoolTaskExecutor() {
        register(this);
    }

    @Override
    public void execute(Runnable task) {
        fixedThreadPoolExecutor.execute(task);
    }

    @Override
    public Future<?> submit(Runnable task) {
        return fixedThreadPoolExecutor.submit(task);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return fixedThreadPoolExecutor.submit(task);
    }

    @Override
    protected String getName() {
        return "fixedTaskExecutor";
    }
}
