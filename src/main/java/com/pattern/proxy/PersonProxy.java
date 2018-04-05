package com.pattern.proxy;

/**
 * Created by chencheng on 2017/10/19.
 */
public class PersonProxy implements Person{
    private Person person;

    public PersonProxy(Person person) {
        this.person = person;
    }
    @Override
    public void eat() {
        System.out.println("eat begin");
        person.eat();
        System.out.println("eat end");
    }
}
