package com.pattern.proxy;

import net.sf.cglib.proxy.Enhancer;

public class PersonProxyCglibTest {

    public static void main(String[] args) {
        PersonProxyCglib proxy=new PersonProxyCglib();
        PersonImpl2 person=(PersonImpl2)proxy.getInstance(new PersonImpl2());
        person.eat();
    }
}
