package com.thread.blockingqueue;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by wanchongyang on 2017/8/13.
 */
public class Main {
    public static void main(String[] args) {
        //1、生成队列数据
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>();
        for (int i = 1; i <= 100; i++) {
            // put阻塞 offer非阻塞
            blockingQueue.offer(i);
        }

        //2、业务数据处理
        ConcurrentHashMap<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        int workerCount = 5;
        final CountDownLatch latch = new CountDownLatch(workerCount);
        Random random = new Random();
        while (workerCount > 0) {
            new Thread(){
                @Override
                public void run() {
                    while (true) {
                        try {
                            // poll非阻塞 take阻塞
                            Integer queueItem = blockingQueue.poll(10, TimeUnit.MILLISECONDS);
                            Thread.sleep(1000);
                            if (queueItem != null) {
                                System.out.println(Thread.currentThread().getName() + "=====" + queueItem);
                                concurrentHashMap.put(queueItem, random.nextInt(900) + 100);
                            } else {
                                break;
                            }

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    latch.countDown();
                }
            }.start();
            workerCount--;
        }

        //3、线程全部执行完成的数据处理
        try {
            latch.await();
            System.out.println("all thread execute ended.");
            int count = 1;
            for(Map.Entry<Integer, Integer> entry : concurrentHashMap.entrySet()) {
                System.out.println("K:" + entry.getKey() + " | V:" + entry.getValue() + " | MV:" + concurrentHashMap.get(count));
                count++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
