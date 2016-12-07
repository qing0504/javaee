package com.thread.blockingqueue;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * <p><b>BlockingDequeTest Description:</b>阻塞栈</p>
 * @author wanchongyang
 * <b>DATE</b> 2016年8月10日 下午2:16:46
 */
public class BlockingDequeTest {   
    public static void main(String[] args) throws InterruptedException {   
            BlockingDeque<String> bDeque = new LinkedBlockingDeque<String>(20);   
            for (int i = 0; i < 30; i++) {   
                //将指定元素添加到此阻塞栈中  
                bDeque.putFirst("" + i);   
                System.out.println("向阻塞栈中添加了元素:" + i);   
                if(i > 18){  
                    //从阻塞栈中取出栈顶元素，并将其移出  
                    System.out.println("从阻塞栈中移出了元素：" + bDeque.pollFirst());  
                }  
            }   
            System.out.println("程序到此运行结束，即将退出----");   
    }   
} 
