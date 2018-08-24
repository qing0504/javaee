package com.ioc.cglib;

/**
 * @author wanchongyang
 * @date 2018/8/23 上午11:43
 */
public class Main {
    public static void main(String[] args) {
        PersonService personService = new SubPersonService();
        personService.dance();
    }
}
