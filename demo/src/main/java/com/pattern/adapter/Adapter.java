package com.pattern.adapter;

/**
 * Created by wanchongyang on 2017/10/10.
 */
public class Adapter extends Source implements Targetable {

    @Override
    public void method2() {
        System.out.println("this is the targetable method!");
    }
}
