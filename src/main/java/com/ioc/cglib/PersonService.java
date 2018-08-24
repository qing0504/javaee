package com.ioc.cglib;

/**
 * @author wanchongyang
 * @date 2018/8/22 下午9:07
 */
public class PersonService {
    public PersonService() {
        System.out.println("I am PersonService.");
    }

    public void dance() {
        System.out.println("I am a dancer.");
        System.out.println("this:" + this);
        this.sing();
    }

    public void sing() {
        System.out.println("I am also a singer.");
    }
}
