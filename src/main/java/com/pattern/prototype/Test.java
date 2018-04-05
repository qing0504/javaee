package com.pattern.prototype;

import java.util.Date;

/**
 * Created by wanchongyang on 2017/10/10.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        Date date = new Date(10000L);
        Prototype prototype = new Prototype("aaa", date);
        System.out.println("prototype-------------" + prototype);
        System.out.println("prototype-------------" + prototype.getName());
        System.out.println("prototype-------------" + prototype.getTime());
        Prototype deepPrototype = (Prototype) prototype.deepClone();
        Prototype lightPrototype = (Prototype) prototype.clone();
        date.setTime(20000L);
        System.out.println("change name to bbb");
        lightPrototype.setName("bbb");
        System.out.println("------------------华丽的分割线------------------------");
        System.out.println("prototype-------------" + prototype.getName());
        System.out.println("prototype-------------" + prototype.getTime());
        System.out.println("lightPrototype-------------" + lightPrototype);
        System.out.println("lightPrototype-------------" + lightPrototype.getName());
        System.out.println("lightPrototype-------------" + lightPrototype.getTime());
        System.out.println("------------------华丽的分割线------------------------");
        System.out.println("deepPrototype-------------" + deepPrototype);
        System.out.println("deepPrototype-------------" + deepPrototype.getName());
        System.out.println("deepPrototype-------------" + deepPrototype.getTime());
    }
}
