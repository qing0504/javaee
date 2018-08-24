package com.spi;

import java.util.ServiceLoader;

/**
 * SPI的全称是Service Provider Interface
 * logback-LogbackServletContainerInitializer
 * @author wanchongyang
 * @date 2018/8/22 下午4:12
 */
public class SPIMain {
    public static void main(String[] args) {
        ServiceLoader<DatabaseInterface> databaseInterfaces = ServiceLoader.load(DatabaseInterface.class);
        databaseInterfaces.forEach(i -> {
            System.out.println(i);
            i.query();
            System.out.println("===========================");
        });
    }
}
