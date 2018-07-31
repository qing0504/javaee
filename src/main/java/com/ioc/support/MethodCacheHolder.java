package com.ioc.support;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wanchongyang
 * @date 2018/7/31 下午7:01
 */
public class MethodCacheHolder {
    private static final Map<Class<?>, Method> METHOD_CACHE_MAP = new ConcurrentHashMap<>(16);

    private MethodCacheHolder() {

    }

    public static void put(Class<?> clazz, Method method) {
        METHOD_CACHE_MAP.putIfAbsent(clazz, method);
    }

    public static Method get(Class<?> methodClass) {
        return METHOD_CACHE_MAP.get(methodClass);
    }
}
