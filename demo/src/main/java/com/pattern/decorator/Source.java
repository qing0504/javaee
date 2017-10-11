package com.pattern.decorator;

/**
 * Created by wanchongyang on 2017/10/11.
 */
public class Source implements Sourceable {
    @Override
    public void method() {
        System.out.println("the original method!");
    }
}
