package com.ioc;

/**
 * @author wanchongyang
 * @date 2018/7/21
 */
public class IOCTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new DefaultApplicationContext();
        UserService userService = (UserService) applicationContext.getBean("userService");
        UserService userService2 = applicationContext.getBean(UserService.class);

        System.out.println("userService == userService2:" + (userService == userService2));
        userService.study();
    }
}
