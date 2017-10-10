package com.pattern.adapter;

/**
 * Created by wanchongyang on 2017/10/10.
 */
public class WrapperAdapterTest {
    public static void main(String[] args) {
        Source source = new Source();
        Targetable target = new Wrapper(source);
        target.method1();
        target.method2();
    }
}
