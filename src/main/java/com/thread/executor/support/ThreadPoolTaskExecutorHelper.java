package com.thread.executor.support;

import org.apache.commons.lang3.StringUtils;

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

    private ThreadPoolTaskExecutorHelper() {}

    @Override
    protected String getName() {
        return null;
    }

    public AsyncTaskExecutor get(String key) {
        if (StringUtils.isBlank(key)) {
            return ThreadPoolTaskExecutorHelper.instance().getInstance(DEFAULT_TASK_EXECUTOR);
        }

        AsyncTaskExecutor asyncTaskExecutor = ThreadPoolTaskExecutorHelper.instance().getInstance(key);
        if (asyncTaskExecutor != null) {
            return asyncTaskExecutor;
        }

        return ThreadPoolTaskExecutorHelper.instance().getInstance(DEFAULT_TASK_EXECUTOR);
    }

}
