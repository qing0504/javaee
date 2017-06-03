package com.thread.blockingqueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by wanchongyang on 2017/6/3.
 */
public class TaskQueueTest {
    private static final int WORKER_COUNT = 10;
    private  static ExecutorService executorService = new ThreadPoolExecutor(WORKER_COUNT,
            WORKER_COUNT,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>());

    public static void main(String[] args) {
        // 模拟队列，添加数据
        for (int i = 1; i <= 1000; i++) {
            TaskQueue.offer(String.valueOf(i));
        }

        for (int j = 0; j < WORKER_COUNT; j++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            String task = TaskQueue.take();
                            System.out.println(Thread.currentThread().getName() + "-------" + task);
                        } catch (InterruptedException e) {
                            System.out.println("error InterruptedException");
                            e.printStackTrace();
                        }
                    }

                }
            });
        }
    }
}
