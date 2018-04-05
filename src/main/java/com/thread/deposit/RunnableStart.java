package com.thread.deposit;

/**
 * Runnable对象仅作为Thread对象的target,其包含的run()方法仅作为线程执行体.
 * 实际的线程对象依然是Thread实例, 只是该Thread线程执行的是target的run()方法.
 * @author wanchongyang
 * <b>DATE</b> 2016年2月17日 上午9:23:57
 */
public class RunnableStart {
	public static void main(String[] args) {
		Runnable runnable = new ConcreteRunnable();
		new Thread(runnable, "first").start();
		new Thread(runnable, "second").start();

		for (int i = 0; i < 10; ++i) {
			System.out.println(Thread.currentThread().getName() + " " + i);
		}
	}

	private static class ConcreteRunnable implements Runnable {

		private int i = 0;

		@Override
		public void run() {
			for (; i < 10; ++i) {
				System.out.println(Thread.currentThread().getName() + " " + i);
			}
		}
	}
}
