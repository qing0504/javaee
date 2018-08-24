package com.ioc.cglib;

/**
 * @author wanchongyang
 * @date 2018/8/23 上午11:42
 */
public class SubPersonService extends PersonService {
    public SubPersonService() {
        System.out.println("I am SubPersonService.");
    }

    @Override
    public void dance() {
        super.dance();
    }
}
