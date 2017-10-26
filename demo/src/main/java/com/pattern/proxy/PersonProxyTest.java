package com.pattern.proxy;

/**
 * Created by chencheng on 2017/10/19.
 *
 * 静态代理
 * 这样通过业务逻辑的代理类调用具体的业务逻辑，同样实现了日志的记录，
 * 而且把日志的记录和具体的业务逻辑进行了分离，这是静态代理。
 *
 * 存在问题
 * 每个方法都得有一个代理类，如果系统要求将程序中的每个方法都使用日志记录，
 * 那代理类的数量可想而知。
 *
 */
public class PersonProxyTest {

    public static void main(String[] args) {
        PersonProxy person=new PersonProxy(new PersonImpl());
        person.eat();
    }
}
