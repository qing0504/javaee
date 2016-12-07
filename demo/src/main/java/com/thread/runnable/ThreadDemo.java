package com.thread.runnable;

/**
 * 实现Runnable接口相比继承Thread类有如下优势：
 *  1、可以避免由于Java的单继承特性而带来的局限；
 *  2、增强程序的健壮性，代码能够被多个线程共享，代码与数据是独立的；
 *  3、适合多个相同程序代码的线程区处理同一资源的情况。
 * 
 * <p><b>ThreadDemo Description:</b></p>
 * @author wanchongyang
 * <b>DATE</b> 2016年8月8日 下午2:54:35
 */
public class ThreadDemo {
	public static void main(String[] args) {
		new MyThread().start();
		new MyThread().start();
		new MyThread().start();
	}
}

class MyThread extends Thread {
	private int ticket = 5;

	public void run() {
		for (int i = 0; i < 10; i++) {
			if (ticket > 0) {
				System.out.println("ticket = " + ticket--);
			}
		}
	}
}