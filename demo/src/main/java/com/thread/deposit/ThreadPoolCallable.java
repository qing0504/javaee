package com.thread.deposit;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolCallable {
	public static void main(String[] args) throws ExecutionException, InterruptedException {

		ExecutorService pool = getThreadPool();

		Future<Integer> task1 = pool.submit(new ConcreteCallable());
		Future<Integer> task2 = pool.submit(new ConcreteCallable());
		System.out.println(task1.isDone());
		System.out.println(task2.isDone());

		pool.shutdown();

		System.out.println("task1 " + task1.get());
		System.out.println("task2 " + task2.get());
	}

	private static ExecutorService getThreadPool() {

		return new ThreadPoolExecutor(5, 20, 20L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
	}

	private static class ConcreteCallable implements Callable<Integer> {
		@Override
		public Integer call() throws Exception {
			int sum = 0;
			for (int i = 0; i < 100; ++i) {
				Thread.sleep(10);
				sum += i;
			}

			return sum;
		}
	}
}
