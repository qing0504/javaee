package com.sample;

/**
 * @author wanchongyang
 * @date 2020/4/1 10:52 下午
 */
public class ThreadLocalTest {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        threadLocal.set("张三");
        System.out.println("one get:" + threadLocal.get());
        // new Thread(() -> {
        //     System.out.println("other thread get:" + threadLocal.get());
        //     System.gc();
        //     System.out.println("other thread after gc get:" + threadLocal.get());
        // }).start();

        threadLocal.set("李四");
        System.gc();
        System.runFinalization();
        Thread.sleep(2000);
        new Thread(() -> {
            System.out.println("other thread after gc get:" + threadLocal.get());
        }).start();

        Thread.sleep(2000);
        System.out.println("last get:" + threadLocal.get());
    }
}
