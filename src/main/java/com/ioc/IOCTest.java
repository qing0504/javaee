package com.ioc;

import com.ioc.impl.EnterpriseServiceImpl;

/**
 * @author wanchongyang
 * @date 2018/7/21
 */
public class IOCTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new DefaultApplicationContext();
        UserService userService = (UserService) applicationContext.getBean("userService");
        UserService userService2 = applicationContext.getBean(UserServiceImpl.class);
        System.out.println("====================singleton===========================");
        System.out.println("userService == userService2:" + (userService == userService2));
        userService.study();
        userService2.study();
        System.out.println("====================prototype===========================");
        EnterpriseService enterpriseService = (EnterpriseService) applicationContext.getBean("enterpriseServiceImpl");
        EnterpriseService enterpriseService2 = applicationContext.getBean(EnterpriseServiceImpl.class);
        System.out.println("enterpriseService == enterpriseService2:" + (enterpriseService == enterpriseService2));
        enterpriseService.manage();
        enterpriseService2.manage();
        System.out.println("=========================================================");
    }
}
