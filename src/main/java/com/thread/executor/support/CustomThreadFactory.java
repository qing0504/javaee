package com.thread.executor.support;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wanchongyang
 * @date 2018/4/26 下午10:48
 */

public class CustomThreadFactory implements ThreadFactory {
    public static Thread.UncaughtExceptionHandler LOGGER_UNCAUGHTEXCEPTIONHANDLER = (t, e) -> System.err.println(t.getName() + ", exception:" + e.getMessage());
    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    private CustomThreadFactory(ThreadGroup group, String prefix) {
        if (group == null) {
            SecurityManager s = System.getSecurityManager();
            this.group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        } else {
            this.group = group;
        }
        if (prefix == null) {
            this.namePrefix = "custom-" + poolNumber.getAndIncrement() + "-thread-";
        } else {
            this.namePrefix = prefix + "-pool_" + poolNumber.getAndIncrement() + "-thread-";
        }
    }

    public CustomThreadFactory(String prefix) {
        this(null, prefix);
    }

    @Override
    public Thread newThread(Runnable r) {

        // 线程组中活跃的线程数
//        this.group.activeCount();

        Thread t = new Thread(this.group, r, this.namePrefix + this.threadNumber.getAndIncrement(), 0);
        t.setDaemon(true);
        t.setUncaughtExceptionHandler(LOGGER_UNCAUGHTEXCEPTIONHANDLER);
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }

        return t;
    }
}
