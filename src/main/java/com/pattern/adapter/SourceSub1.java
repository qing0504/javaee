package com.pattern.adapter;

/**
 * Created by wanchongyang on 2017/10/10.
 */
public class SourceSub1 extends Wrapper2 {
    @Override
    public void method1(){
        System.out.println("the sourceable interface's first Sub1!");
    }
}