package com.thread.executor;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author wanchongyang
 * @date 2018/5/10 下午2:14
 */
public class CompletableFutureDemo {
    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        // 结果集
        List<String> list = new ArrayList<>();

        final ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("custom-executor-thread-%d")
                .setDaemon(true)
                .build();

        final ExecutorService executorService = new ThreadPoolExecutor(
                10,
                10,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                threadFactory
        );

        List<Integer> taskList = Arrays.asList(2, 1, 3, 4, 5, 6, 7, 8, 9, 10);
        // 全流式处理转换成CompletableFuture[]+组装成一个无返回值CompletableFuture，join等待执行完毕。返回结果whenComplete获取
        CompletableFuture[] cfs = taskList.stream()
                .map(integer -> CompletableFuture.supplyAsync(() -> calc(integer), executorService)
                        .thenApply(h -> Integer.toString(h))
                        .whenComplete((s, e) -> {
                            System.out.println("任务" + s + "完成!result=" + s + "，异常 e=" + e + "," + new Date());
                            list.add(s);
                        })
                ).toArray(CompletableFuture[]::new);
        // 封装后无返回值，必须自己whenComplete()获取
        CompletableFuture.allOf(cfs).join();
        System.out.println("list=" + list + ",耗时=" + (System.currentTimeMillis() - start));
    }

    public static Integer calc(Integer i) {
        try {
            if (i == 1) {
                //任务1耗时3秒
                Thread.sleep(3000);
            } else if (i == 5) {
                //任务5耗时5秒
                Thread.sleep(5000);
            } else {
                //其它任务耗时1秒
                Thread.sleep(1000);
            }
            System.out.println("task线程：" + Thread.currentThread().getName()
                    + "任务i=" + i + ",完成！+" + new Date());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i;
    }
}
