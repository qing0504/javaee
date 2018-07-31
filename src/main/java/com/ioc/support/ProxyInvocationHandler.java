package com.ioc.support;

import com.thread.executor.support.*;
import org.apache.commons.lang3.StringUtils;

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
        Method realMethod = MethodCacheHolder.get(method.getClass());
        if (realMethod == null) {
            Optional<Method> first = Arrays.stream(this.target.getClass().getMethods()).filter(m -> method.getName().equals(m.getName())).findFirst();
            realMethod = first.get();

            MethodCacheHolder.put(method.getClass(), realMethod);
        }

        Async async = realMethod.getAnnotation(Async.class);
        if (async != null) {
            AsyncTaskExecutor asyncTaskExecutor = null;
            if (StringUtils.isBlank(async.value())) {
                asyncTaskExecutor = ThreadPoolTaskExecutorHelper.instance().get(ThreadPoolTaskExecutorHelper.DEFAULT_TASK_EXECUTOR);
            } else {
                asyncTaskExecutor = ThreadPoolTaskExecutorHelper.instance().get(async.value());
            }

            if (asyncTaskExecutor == null) {
                asyncTaskExecutor = ThreadPoolTaskExecutorHelper.instance().get(ThreadPoolTaskExecutorHelper.DEFAULT_TASK_EXECUTOR);
            }
            // 目标方法异步执行
            Class<?> returnType = method.getReturnType();
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
}
