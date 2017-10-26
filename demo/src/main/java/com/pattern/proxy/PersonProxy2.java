package com.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by chencheng on 2017/10/19.
 */
public class PersonProxy2 implements InvocationHandler {
    private Object delegate;

    //绑定代理对象
    public Object bind(Object delegate){
        this.delegate=delegate;
        return Proxy.newProxyInstance(delegate.getClass().getClassLoader(), delegate.getClass().getInterfaces(), this);
    }
    //针对接口编程
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result=null;
        System.out.println("开始业务逻辑处理");
        result=method.invoke(delegate, args);
        System.out.println("结束业务逻辑处理");
        return result;
    }
}
