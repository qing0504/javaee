package com.thread.interrupt;

/**
 * 如果线程在调用sleep（）方法前被中断，那么该中断称为待决中断，
 * 它会在刚调用sleep（）方法时，立即抛出InterruptedException异常。
 * 
 * <p><b>PendingInterrupt Description:</b>待决中断</p>
 * @author wanchongyang
 * <b>DATE</b> 2016年8月8日 上午10:04:28
 */
public class PendingInterrupt {

	public static void main(String[] args) {
		// 如果输入了参数，则在mian线程中中断当前线程（亦即main线程）
		if (args.length > 0) {
			Thread.currentThread().interrupt();
		}
		// 获取当前时间
		long startTime = System.currentTimeMillis();
		try {
			Thread.sleep(2000);
			System.out.println("was NOT interrupted");
		} catch (InterruptedException x) {
			System.out.println("was interrupted");
		}
		// 计算中间代码执行的时间
		System.out.println("elapsedTime=" + (System.currentTimeMillis() - startTime));
	}

}
