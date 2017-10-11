package com.pattern.facade;

/**
 * Created by wanchongyang on 2017/10/11.
 */
public class User {
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.startup();
        computer.shutdown();
    }
}
