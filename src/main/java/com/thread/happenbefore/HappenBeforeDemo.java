package com.thread.happenbefore;

public class HappenBeforeDemo {

    public static void main(String[] args) {
        Runnable runA = new Runnable() {

            @Override
            public void run() {
                LazySingleton.getInstance();
            }
        };

        Runnable runB = new Runnable() {

            @Override
            public void run() {
                LazySingleton instance = LazySingleton.getInstance();
                instance.getSomeField();
            }
        };

        try {
            // 启动第一个线程
            Thread threadA = new Thread(runA, "threadA");
            threadA.start();
            Thread.sleep(500);

            // 启动第二个线程
            Thread threadB = new Thread(runB, "threadB");
            threadB.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
