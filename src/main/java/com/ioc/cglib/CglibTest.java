package com.ioc.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author wanchongyang
 * @date 2018/8/22 下午9:12
 */
public class CglibTest {
    public static void main(String[] args) {
        // 代理类class文件存入本地磁盘
        // String dir = System.getProperty("user.dir");
        // System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, dir);
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
        enhancer.setCallback(new CglibProxyInterceptor());
        PersonService proxy = (PersonService) enhancer.create();
        proxy.dance();
    }
}
