package com.pattern.singleton;

/**
 * Created by wanchongyang on 2017/10/10.
 */
public class Test {
    public static void main(String[] args) {
        LazySingleton lazySingleton1 = LazySingleton.getInstance();
        LazySingleton lazySingleton2 = LazySingleton.getInstance();
        System.out.println(lazySingleton1 == lazySingleton2);
    }
}
