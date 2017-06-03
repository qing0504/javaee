package com.thread.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by wanchongyang on 2017/6/3.
 */
public class TaskQueue {
    private static BlockingQueue<String> taskQueue = new LinkedBlockingDeque<>();

    public static boolean offer(String task) {
        return taskQueue.offer(task);
    }

    public static String take () throws InterruptedException {
        return taskQueue.take();
    }
}
