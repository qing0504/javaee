package com.ioc.support;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author wanchongyang
 * @date 2018/7/22
 */
public class ProxyInvocationHandler implements InvocationHandler {
    private Object target;

    public ProxyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        System.out.println(method.getName() + " is invoked. begin");
        result = method.invoke(target, args);
        System.out.println(method.getName() + " is invoked. end");
        return result;
    }
}
