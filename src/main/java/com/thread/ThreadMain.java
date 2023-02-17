package com.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @author wanchongyang
 * @date 2022/5/15 13:05
 */
public class ThreadMain {
    public static void main(String[] args) {
        // 问题：运行这个应用，Java 至少会创建几个线程呢？

        // Reference Handler：该线程负责将待回收的对象管理起来，等待回收
        //
        // Finalizer：该线程负责从 Reference Handler 获取待回收对象，检查该对象是否实现了 finalize 方法，如果实现了该方法而且没有被调用过，就会调用该方法，使对象继续存活
        //
        // Attach Listener：该线程负责接收外部命令，并且把该命令执行的结果返回给发送者，比如：jps，jmp 等等
        //
        // Single Dispatcher：Attach Listener 在接收到命令之后，会交给该线程进行分发到不同的模块去处理并获得处理结果
        //
        // Common-Cleaner：该线程是 JDK9 之后新增的守护线程，用来更高效的处理垃圾回收
        //
        // Monitor Ctrl-Break：该线程用于应用监测以及用于排查问题
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("Thread id: " + threadInfo.getThreadId() + ", thread name: " + threadInfo.getThreadName() + ", isDaemon: " + threadInfo.isDaemon());
        }

        System.out.println("Hello World!");
    }
}
