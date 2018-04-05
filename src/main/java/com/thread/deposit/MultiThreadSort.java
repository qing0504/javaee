package com.thread.deposit;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * join()方法可以一个让线程等待另一个线程执行完成: 调用线程被阻塞,知道被join()的线程执行完成. 
 * 该方法通常由主线程调用,将大问题划分成小问题,每个小问题分配一个线程执行,当所有的小问题处理完成,
 * 再由主线程来做最后处理.如多线程排序,将一个大的排序任务分割为几个小块,分配给几个线程,当所有子线程执行完成后,
 * 再由主线程进行归并
 * @author wanchongyang
 * <b>DATE</b> 2016年2月17日 下午3:47:34
 */

public class MultiThreadSort {
	private static final int THREAD_COUNT = 12; /* 12个线程分段排序 */
	private static final int NUMBER_COUNT = 201600;
	private static final int PER_COUNT = NUMBER_COUNT / THREAD_COUNT;
	private static final int RANDOM_LIMIT = 10000000;

	public static void main(String[] args) throws InterruptedException {

		// 为数组分配随机值, 为了方便查看, 为其分配10000000以内的值
		Random random = new Random();
		int[] array = new int[NUMBER_COUNT];
		for (int i = 0; i < NUMBER_COUNT; ++i) {
			array[i] = random.nextInt(RANDOM_LIMIT);
		}

		List<Thread> threadList = new LinkedList<>();
		for (int index = 0; index < THREAD_COUNT; ++index) {
			Thread t = new Thread(new SortRunnable(array, index * PER_COUNT, (index + 1) * PER_COUNT));
			t.start();
			threadList.add(t);
		}

		// 等待线程排序完成
		join(threadList);

		// 分段合并
		int[] result = merge(array, PER_COUNT, THREAD_COUNT);
		if (check(result)) {
			System.out.println("correct");
		}
	}

	private static boolean check(int[] array) {
		for (int i = 0; i < array.length - 1; ++i) {
			if (array[i] > array[i + 1]) {
				System.out.println("error");
				return false;
			}
		}
		return true;
	}

	private static void join(List<Thread> threads) throws InterruptedException {
		for (Thread thread : threads) {
			thread.join();
		}
	}

	/**
	 * 分段合并
	 * 
	 * @param array
	 *            已经分段排好序的数组
	 * @param size
	 *            每段的长度
	 * @param count
	 *            一共的段数
	 * @return
	 */
	private static int[] merge(int[] array, int size, int count) {

		// indexes保存array每段的起始位置
		int[] indexes = new int[count];
		for (int i = 0; i < count; ++i) {
			indexes[i] = i * size;
		}

		int[] result = new int[array.length];
		// i保存result下标
		for (int i = 0; i < result.length; ++i) {

			int minNumber = Integer.MAX_VALUE;
			int minIndex = 0;
			// 内层for循环的作用: 找出这count段中最小的那个值
			for (int index = 0; index < indexes.length; ++index) {
				// indexes[index]: 当前段的起始位置
				if ((indexes[index] < (index + 1) * size) && (array[indexes[index]] < minNumber)) {
					minNumber = array[indexes[index]];
					minIndex = index;
				}
			}

			result[i] = minNumber;
			indexes[minIndex]++;
		}

		return result;
	}

	private static class SortRunnable implements Runnable {

		private int[] array;
		private int start;
		private int end;

		public SortRunnable(int[] array, int start, int end) {
			this.array = array;
			this.start = start;
			this.end = end;
		}

		@Override
		public void run() {
			// 分段排序
			Arrays.sort(array, start, end);
		}
	}
}
