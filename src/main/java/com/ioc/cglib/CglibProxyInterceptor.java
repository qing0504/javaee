package com.ioc.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author wanchongyang
 * @date 2018/8/22 下午9:09
 */
public class CglibProxyInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object sub, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println(method.getName() + " invoke before.");
        Object object = methodProxy.invokeSuper(sub, objects);
        System.out.println(method.getName() + " invoke after.");
        return object;
    }
}
