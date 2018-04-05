package com.pattern.abstractfacoty;

import com.pattern.factory.Sender;

/**
 * 抽象工厂模式
 * Created by wanchongyang on 2017/10/10.
 */
public class AbstractFactoryTest {
    public static void main(String[] args) {
        Provider provider = new SendMailFactory();
        Sender sender = provider.produce();
        sender.send();
    }
}
