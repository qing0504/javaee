package com.thread.deposit;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

/**
 * 由于实现Runnable和Callable的方式可以让多个线程共享同一个target,
 * 因此适用于多个线程处理同一份资源的情况,从而将CPU/代码/数据分开.
 * @author wanchongyang
 * <b>DATE</b> 2016年2月17日 上午9:31:35
 */
public class CallableStart {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		RunnableFuture<Integer> task = new FutureTask<>(new ConcreteCallable());
		new Thread(task).start();

		while (true) {
			System.out.println("主线程在干其他事情...");
			if (task.isDone()) {
				System.out.println("子线程返回值: " + task.get());
				break;
			}
			Thread.sleep(5);
		}
	}

	private static class ConcreteCallable implements Callable<Integer> {

		@Override
		public Integer call() throws Exception {
			int total = 0;
			for (int i = 0; i < 100; ++i) {
				Thread.sleep(10);
				total += i;
			}

			return total;
		}
	}
}
