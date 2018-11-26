package com.sample;

/**
 * @author wanchongyang
 * @date 2018/11/25 9:05 PM
 */
public class ClassPathTest {
    public static void main(String[] args) {
        // 得到的是当前类class文件的URI目录。不包括自己
        System.out.println(ClassPathTest.class.getResource(""));
        // 得到的是当前的classpath的绝对URI路径
        System.out.println(ClassPathTest.class.getResource("/"));
        // 得到的是当前的classpath的绝对URI路径
        System.out.println(ClassPathTest.class.getClassLoader().getResource(""));
        // 得到的是当前的classpath的绝对URI路径
        System.out.println(ClassLoader.getSystemResource(""));
        // 得到的是当前的classpath的绝对URI路径
        System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
    }
}
