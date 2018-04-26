package com.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试堆溢出
 * 虚拟机参数：-Xms20M -Xmx20M -XX:+HeapDumpOnOutOfMemoryError
 * @author wanchongyang
 * @date 2018/4/26 上午9:49
 */
public class HeapOverflowTest {
    public static void main(String[] args) {
        // Java堆唯一的作用就是存储对象实例，只要保证不断创建对象并且对象不被回收，那么对象数量达到最大堆容量限制后就会产生内存溢出异常了
        List<HeapOverflowTest> list = new ArrayList<>();
        while (true) {
            list.add(new HeapOverflowTest());
        }
    }
}
