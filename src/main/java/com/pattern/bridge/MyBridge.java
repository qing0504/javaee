package com.pattern.bridge;

/**
 * Created by wanchongyang on 2017/10/11.
 */
public class MyBridge extends AbstractBridge {
    public void method(){
        getSource().method();
    }
}
