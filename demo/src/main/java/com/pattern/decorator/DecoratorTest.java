package com.pattern.decorator;

/**
 * Created by wanchongyang on 2017/10/11.
 */
public class DecoratorTest {
    public static void main(String[] args) {
        Sourceable source = new Source();
        Sourceable obj = new Decorator(source);
        obj.method();
    }
}
