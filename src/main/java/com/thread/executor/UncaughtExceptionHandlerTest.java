package com.thread.executor;

import com.common.utils.ConcurrentUtils;
import com.thread.executor.support.AsyncExecutor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 只有通过execute提交的任务，才能将它抛出的异常交给UncaughtExceptionHandler，而通过submit提交的任务，
 * 无论是抛出的未检测异常还是已检查异常，都将被认为是任务返回状态的一部分。如果一个由submit提交的任务由于抛出了异常而结束，
 * 那么这个异常将被Future.get封装在ExecutionException中重新抛出。
 * @author wanchongyang
 * @date 2018/8/1 下午3:20
 */
public class UncaughtExceptionHandlerTest {
    public static void main(String[] args) {
        System.out.println("==========execute============");
        AsyncExecutor.instance().execute(() -> {
            int i = 1/0;
            System.out.println("ha ha.");
        });
        ConcurrentUtils.sleep(1);

        System.out.println("==========submit============");
        Future<Integer> future = AsyncExecutor.instance().submit(() -> {
            System.out.println("wow wow.");
            Integer num = null;
            int j = num.intValue();
            return j;
        });

        while (true) {
            if (future.isDone()) {
                try {
                    System.out.println(future.get());
                } catch (InterruptedException | ExecutionException e) {
                    System.err.println(e.getClass());
                    System.err.println("Exception: " + e.getMessage());
                }

                break;
            }
        }
        ConcurrentUtils.sleep(1);
        System.out.println("it is over.");
    }
}
