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
    }
}
