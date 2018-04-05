package com.thread.deposit;

public class ThreadPriority {
	public static void main(String[] args) {
		Thread low = new Thread(new PriorityRunnable(), "low");
		low.setPriority(Thread.MIN_PRIORITY);

		Thread mid = new Thread(new PriorityRunnable(), "mid");
		mid.setPriority(Thread.NORM_PRIORITY);

		Thread high = new Thread(new PriorityRunnable(), "high");
		high.setPriority(Thread.MAX_PRIORITY);

		start(low, mid, high);
	}

	private static void start(Thread... threads) {
		for (Thread thread : threads) {
			thread.start();
		}
	}

	private static class PriorityRunnable implements Runnable {

		@Override
		public void run() {
			for (int i = 0; i < 10; ++i) {
				System.out.println(Thread.currentThread().getName() + " " + i);
			}
		}
	}
}
