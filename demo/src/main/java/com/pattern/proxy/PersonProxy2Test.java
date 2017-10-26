package com.pattern.proxy;

/**
 * 动态代理
 * <p>
 * 利用java的反射机制，增加了动态代理的功能，即原来是对每个方法实现了一个代理类，
 * 现在是针对每个功能增加了一个代理类。
 * <p>
 * 定义
 * 代理模式就是给一个对象提供一个代理对象，由这个代理对象控制对原对象的引用，
 * 使代理类在客户端和原对象之间起到一个中介的作用。
 *
 * 原理
 * 三组成部分：抽象目标类、具体目标类和代理类
 *
 * 优点
 * 使用代理模式，能够在不改变原来代码功能的基础上对某一对象进行额外的控制，从而更好地体现了
 * 面向对象中单一职责的思想
 *
 */
public class PersonProxy2Test {

    public static void main(String[] args) {
        PersonProxy2 proxy=new PersonProxy2();
        Person person= (Person) proxy.bind(new PersonImpl());
        person.eat();
    }
}
