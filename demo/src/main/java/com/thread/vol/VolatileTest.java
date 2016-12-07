package com.thread.vol;

/**
 * Volatile修饰的成员变量在每次被线程访问时，都强迫从共享内存中重读该成员变量的值。
 * 而且，当成员变量发生变化时，强迫线程将变化值回写到共享内存。这样在任何时刻，
 * 两个不同的线程总是看到某个成员变量的同一个值。
 * 
 * volatile是一种稍弱的同步机制，在访问volatile变量时不会执行加锁操作，也就不会执行线程阻塞，因此volatilei变量是一种比synchronized关键字更轻量级的同步机制。
 * 使用建议：在两个或者更多的线程需要访问的成员变量上使用volatile。当要访问的变量已在synchronized代码块中，或者为常量时，没必要使用volatile。
 * 由于使用volatile屏蔽掉了JVM中必要的代码优化，所以在效率上比较低，因此一定在必要时才使用此关键字。
 * 
 * 编译器和处理器在重排序时，会遵守数据依赖性，编译器和处理器不会改变存在数据依赖关系的两个操作的执行顺序。（单个）
 * 不同处理器之间和不同线程之间的数据依赖性不被编译器和处理器考虑。
 * 
 * <p><b>VolatileTest Description:</b></p>
 * @author wanchongyang
 * <b>DATE</b> 2016年8月8日 上午11:41:08
 */
public class VolatileTest implements Runnable {
	// value变量没有被标记为volatile
	private int value;
	// missedIt变量被标记为volatile
	private volatile boolean missedIt;
	// creationTime不需要声明为volatile，因为代码执行中它没有发生变化
	private long creationTime;

	public VolatileTest() {
		value = 10;
		missedIt = false;
		// 获取当前时间，亦即调用Volatile构造函数时的时间
		creationTime = System.currentTimeMillis();
	}

	public void run() {
		print("entering run()");

		// 循环检查value的值是否不同
		while (value < 20) {
			// 如果missedIt的值被修改为true，则通过break退出循环
			if (missedIt) {
				// 进入同步代码块前，将value的值赋给currValue
				int currValue = value;
				// 在一个任意对象上执行同步语句，目的是为了让该线程在进入和离开同步代码块时，
				// 将该线程中的所有变量的私有拷贝与共享内存中的原始值进行比较，
				// 从而发现没有用volatile标记的变量所发生的变化
				Object lock = new Object();
				synchronized (lock) {
					// 不做任何事
				}
				// 离开同步代码块后，将此时value的值赋给valueAfterSync
				int valueAfterSync = value;
				print("in run() - see value=" + currValue + ", but rumor has it that it changed!");
				print("in run() - valueAfterSync=" + valueAfterSync);
				break;
			}
		}
		print("leaving run()");
	}

	public void workMethod() throws InterruptedException {
        print("entering workMethod()");  
        print("in workMethod() - about to sleep for 2 seconds");  
        Thread.sleep(2000);  
        //仅在此改变value的值  
        missedIt = true;  
//      value = 50;  
        print("in workMethod() - just set value=" + value);  
        print("in workMethod() - about to sleep for 5 seconds");  
        Thread.sleep(5000);  
        //仅在此改变missedIt的值  
//      missedIt = true;  
        value = 50;  
        print("in workMethod() - just set missedIt=" + missedIt);  
        print("in workMethod() - about to sleep for 3 seconds");  
        Thread.sleep(3000);  
        print("in workMethod() - current value=" + value);
        print("leaving workMethod()"); 
	}

	/*
	 * 该方法的功能是在要打印的msg信息前打印出程序执行到此所化去的时间，以及打印msg的代码所在的线程
	 */
	private void print(String msg) {
		// 使用java.text包的功能，可以简化这个方法，但是这里没有利用这一点
		long interval = System.currentTimeMillis() - creationTime;
		String tmpStr = "    " + (interval / 1000.0) + "000";
		int pos = tmpStr.indexOf(".");
		String secStr = tmpStr.substring(pos - 2, pos + 4);
		String nameStr = "        " + Thread.currentThread().getName();
		nameStr = nameStr.substring(nameStr.length() - 8, nameStr.length());
		System.out.println(secStr + " " + nameStr + ": " + msg);
	}

	public static void main(String[] args) {
		try {
			// 通过该构造函数可以获取实时时钟的当前时间
			VolatileTest vol = new VolatileTest();

			// 稍停100ms，以让实时时钟稍稍超前获取时间，使print（）中创建的消息打印的时间值大于0
			Thread.sleep(100);

			Thread t = new Thread(vol);
			t.start();

			// 休眠100ms，让刚刚启动的线程有时间运行
			Thread.sleep(100);
			// workMethod方法在main线程中运行
			vol.workMethod();
		} catch (InterruptedException x) {
			System.err.println("one of the sleeps was interrupted");
		}
	}
}
