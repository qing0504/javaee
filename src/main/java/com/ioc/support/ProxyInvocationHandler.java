package com.ioc.support;

import com.thread.executor.support.*;
import org.apache.commons.lang3.StringUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

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
        Method realMethod = MethodCacheHolder.get(method.getName());
        Class<?> targetClass = this.target.getClass();
        if (realMethod == null) {
            Optional<Method> first = Arrays.stream(targetClass.getDeclaredMethods()).filter(m -> method.getName().equals(m.getName())).findFirst();
            realMethod = first.get();

            MethodCacheHolder.put(targetClass.getName() + "_" + method.getName(), realMethod);
        }

        Async async = realMethod.getAnnotation(Async.class);
        if (async != null) {
            AsyncTaskExecutor asyncTaskExecutor = ThreadPoolTaskExecutorHelper.instance().get(async.value());
            // 目标方法异步执行
            Class<?> returnType = method.getReturnType();
            // 有返回值方法的异步调用
            if (Future.class == returnType) {
                Future<Object> futureResult = asyncTaskExecutor.submit(() -> {
                    try {
                        AsyncResult asyncResult = (AsyncResult) method.invoke(target, args);
                        return asyncResult.get();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                    return null;
                });

                return futureResult;
            } else {
                asyncTaskExecutor.execute(() -> {
                    try {
                        method.invoke(target, args);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });
            }

        } else {
            result = method.invoke(target, args);
        }

        System.out.println(method.getName() + " is invoked. end");
        return result;
    }

    @Override
    public String toString() {
        return "ProxyInvocationHandler{" +
                "target=" + target +
                '}';
    }
}
