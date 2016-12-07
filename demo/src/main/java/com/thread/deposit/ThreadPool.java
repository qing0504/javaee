package com.thread.deposit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ThreadPool {
	public static void main(String[] args) {
        ExecutorService pool = getThreadPool();
        pool.submit(new ConcreteRunnable());
        pool.submit(new ConcreteRunnable());
        pool.shutdown();
    }

    private static ExecutorService getThreadPool() {

        return Executors.newCachedThreadPool(new ThreadFactory() {

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        });
        // return Executors.newCachedThreadPool();
        // return Executors.newFixedThreadPool(2);
        // return Executors.newSingleThreadExecutor();
    }

    private static class ConcreteRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; ++i) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        }
    }
}
