package com.ioc.support;

/**
 * @author wanchongyang
 * @date 2018/7/21
 */
public interface ApplicationContext {
    Object getBean(String beanName);

    <T> T getBean(Class<T> t);
}
