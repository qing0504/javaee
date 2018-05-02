package com.thread.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author wanchongyang
 * @date 2018/4/26 下午10:56
 */
public class FutureTest {
    private static final int THREAD_COUNT = 10;

    private static final Random RANDOM = new Random();


    public static void main(String[] args) throws InterruptedException {
        List<Callable<Integer>> callableList = new ArrayList<>();
        for (int i = 0; i < THREAD_COUNT; i++) {
            callableList.add(() -> {
                int randomInt = RANDOM.nextInt(100);
                Thread.sleep(10);
                System.out.println(Thread.currentThread().getName() + "========" + randomInt);
                return randomInt;
            });
        }

        List<Future<Integer>> futureList = AsyncExecutor.instance().invokeAll(callableList, 10, TimeUnit.SECONDS);
        futureList.stream().forEach(f -> {
            try {
                System.out.println(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}
