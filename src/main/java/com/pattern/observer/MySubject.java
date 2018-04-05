package com.pattern.observer;

/**
 * Created by wanchongyang on 2017/10/11.
 */
public class MySubject extends AbstractSubject {

    @Override
    public void operation() {
        System.out.println("update self!");
        notifyObservers();
    }

}
