package com.pattern.observer;

/**
 * Created by wanchongyang on 2017/10/11.
 */
public class Observer1 implements Observer {

    @Override
    public void update() {
        System.out.println("observer1 has received!");
    }
}
