package com.pattern.mediator;

/**
 * Created by wanchongyang on 2017/10/11.
 */
public class Test {
    public static void main(String[] args) {
        Mediator mediator = new MyMediator();
        mediator.createMediator();
        mediator.workAll();
    }
}
