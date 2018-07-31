package com.thread.executor.support;

/**
 * @author wanchongyang
 * @date 2018/7/31 下午4:43
 */
public class ThreadPoolTaskExecutorHelper extends AbstractThreadPoolTaskExecutor {
    public static final String DEFAULT_TASK_EXECUTOR = "taskExecutor";
    public static ThreadPoolTaskExecutorHelper instance() {
        return ThreadPoolTaskExecutorHolder.INSTANCE;
    }

    private static class ThreadPoolTaskExecutorHolder {
        private static ThreadPoolTaskExecutorHelper INSTANCE = new ThreadPoolTaskExecutorHelper();
    }

    static {
        new DefaultThreadPoolTaskExecutor();
        new FixedThreadPoolTaskExecutor();
    }

    @Override
    protected String getName() {
        return null;
    }

    public AsyncTaskExecutor get(String key) {
        return ThreadPoolTaskExecutorHelper.instance().getInstance(key);
    }

}
