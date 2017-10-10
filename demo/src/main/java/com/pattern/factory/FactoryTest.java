package com.pattern.factory;

/**
 * Created by wanchongyang on 2017/10/10.
 */
public class FactoryTest {
    public static void main(String[] args) {
        // 普通工厂模式
        OrdinarySendFactory ordinarySendFactory = new OrdinarySendFactory();
        Sender mailSender = ordinarySendFactory.produce("mail");
        mailSender.send();

        Sender smsSender = ordinarySendFactory.produce("sms");
        smsSender.send();
        System.out.println("---------------华丽的分割线----------------");
        // 多个工厂方法模式
        MultiSendFactory multiSendFactory = new MultiSendFactory();
        Sender mailSender2 = multiSendFactory.produceMail();
        mailSender2.send();

        Sender smsSender2 = multiSendFactory.produceSms();
        smsSender2.send();
        System.out.println("---------------华丽的分割线----------------");

        // 静态工厂方法模式
        Sender mailSender3 = StaticSendFactory.produceMail();
        mailSender3.send();

        Sender smsSender3 = StaticSendFactory.produceSms();
        smsSender3.send();
        System.out.println("---------------华丽的分割线----------------");
    }
}
