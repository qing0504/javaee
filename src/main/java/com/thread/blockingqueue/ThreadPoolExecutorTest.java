package com.thread.blockingqueue;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author wanchongyang
 * @date 2017/10/20
 */
public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        //1、生成队列数据
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>();
        for (int i = 1; i <= 100; i++) {
            // put阻塞 offer非阻塞
            blockingQueue.offer(i);
        }

        //2、业务数据处理
        ConcurrentHashMap<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>(16);
        int workerCount = 5;
        ThreadPoolExecutor executor = new ThreadPoolExecutor(workerCount,
                workerCount,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                Executors.defaultThreadFactory()
        );
        //ThreadPoolExecutor executor = new ThreadPoolExecutor(0,
        //        workerCount,
        //        1L,
        //        TimeUnit.SECONDS,
        //        new SynchronousQueue<>()
        //);
        Random random = new Random();
        while (workerCount-- > 0) {
            System.out.println("workerCount-----------------" + workerCount);
            executor.execute(() -> {
                while (true) {
                    try {
                        // poll非阻塞 take阻塞
                        Integer queueItem = blockingQueue.poll(10, TimeUnit.MILLISECONDS);
                        Thread.sleep(100);
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

                System.out.println(Thread.currentThread().getName() + " is finished.");
                //Thread.currentThread().interrupt();
            });
        }

        // 3、线程全部执行完成的数据处理
        executor.isShutdown();
        //try {
        //    executor.awaitTermination(1L, TimeUnit.HOURS);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        while(true){
            //if(executor.isTerminated()){
            if (executor.getActiveCount() == 0) {
                System.out.println("all thread execute ended.");
                executor.shutdownNow();
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int count = 1;
        for(Map.Entry<Integer, Integer> entry : concurrentHashMap.entrySet()) {
            System.out.println("K:" + entry.getKey() + " | V:" + entry.getValue() + " | MV:" + concurrentHashMap.get(count));
            count++;
        }

    }
}
