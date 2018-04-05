package com.pattern.adapter;

/**
 * Created by wanchongyang on 2017/10/10.
 */
public class AdapterTest {
    public static void main(String[] args) {
        Targetable target = new Adapter();
        target.method1();
        target.method2();
    }
}
