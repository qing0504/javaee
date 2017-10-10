package com.pattern.factory;

/**
 * Created by wanchongyang on 2017/10/10.
 */
public class SmsSender implements Sender {
    @Override
    public void send() {
        System.out.println("this is sms sender!");
    }
}
