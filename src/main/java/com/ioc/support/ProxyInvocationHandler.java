package com.ioc.support;

import com.thread.executor.support.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Future;

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
        Class<?> targetClass = this.target.getClass();
        String key = method.toGenericString();
        Method realMethod = MethodCacheHolder.get(key);
        if (realMethod == null) {
            realMethod = targetClass.getDeclaredMethod(method.getName(), method.getParameterTypes());

            MethodCacheHolder.put(key, realMethod);
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
