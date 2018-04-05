package com.thread.interrupt;

/**
 * Thread.interrupted（）方法来检查当前线程的中断状态（并隐式重置为false）。
 * 又由于它是静态方法，因此不能在特定的线程上使用，而只能报告调用它的线程的中断状态，
 * 如果线程被中断，而且中断状态尚不清楚，那么，这个方法返回true。与isInterrupted（）不同，
 * 它将自动重置中断状态为false，第二次调用Thread.interrupted（）方法，总是返回false，除非中断了线程。
 * 
 * 
 * yield和join方法的使用。
 * join方法用线程对象调用，如果在一个线程A中调用另一个线程B的join方法，线程A将会等待线程B执行完毕后再执行。
 * yield可以直接用Thread类调用，yield让出CPU执行权给同等级的线程，如果没有相同级别的线程在等待CPU的执行权，则该线程继续执行。
 * <p><b>InterruptReset Description:</b></p>
 * @author wanchongyang
 * <b>DATE</b> 2016年8月8日 上午10:20:04
 */
public class InterruptReset {
	public static void main(String[] args) {
		System.out.println("Point X: Thread.interrupted()=" + Thread.interrupted());
		
		Thread.currentThread().interrupt();
		
		System.out.println("Point Y: Thread.interrupted()=" + Thread.interrupted());
		System.out.println("Point Z: Thread.interrupted()=" + Thread.interrupted());
	}
}
