package com.thread.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 
 * <p><b>BlockingQueueTest Description:</b>阻塞队列</p>
 * @author wanchongyang
 * <b>DATE</b> 2016年8月10日 下午2:12:14
 */
public class BlockingQueueTest{   
    public static void main(String[] args) throws InterruptedException {   
            BlockingQueue<String> bqueue = new ArrayBlockingQueue<String>(20);   
            for (int i = 0; i < 30; i++) {   
                    //将指定元素添加到此队列中   
                    bqueue.put("" + i);   
                    System.out.println("向阻塞队列中添加了元素:" + i);   
                    if(i > 18){  
                        //从队列中获取队头元素，并将其移出队列  
                        System.out.println("从阻塞队列中移除元素：" + bqueue.take());  
                    }  
            }   
            System.out.println("程序到此运行结束，即将退出----");   
    }   
} 
