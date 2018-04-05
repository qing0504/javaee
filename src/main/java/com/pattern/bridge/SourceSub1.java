package com.pattern.bridge;

/**
 * Created by wanchongyang on 2017/10/11.
 */
public class SourceSub1 implements Sourceable {

    @Override
    public void method() {
        System.out.println("this is the first sub!");
    }
}
