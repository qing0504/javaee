package com.classloader;

import com.common.utils.ClassLoaderUtil;

/**
 * @author wanchongyang
 * @date 2017/10/24
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(ClassLoaderUtil.getClassPath());
        System.out.println(ClassLoaderUtil.getLoader());

        // 当前线程的类加载器
        System.out.println(Thread.currentThread().getContextClassLoader());
        // 当前类的类加载器
        System.out.println(Test.class.getClassLoader());
        // 系统初始的类加载器
        System.out.println(ClassLoader.getSystemClassLoader());
    }
}
