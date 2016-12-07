package com.thread.deposit;

/**
 * 继承Thread类来创建线程类时,多个线程之间无法共享线程类的实例变量.
 * @author wanchongyang
 * <b>DATE</b> 2016年2月17日 上午9:23:41
 */
public class ThreadStart {
	public static void main(String[] args) {
		new ConcreteThread("first").start();
		new ConcreteThread("second").start();

		for (int i = 0; i < 10; ++i) {
			System.out.println(Thread.currentThread().getName() + ": " + i);
		}
	}

	private static class ConcreteThread extends Thread {
		private int i = 0;
		
		public ConcreteThread(String name) {
			super(name);
		}

		@Override
		public void run() {
			for (; i < 10; ++i) {
				System.out.println(getName() + ": " + i);
			}
		}
	}
}
