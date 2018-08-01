package com.ioc;

import com.ioc.impl.EnterpriseServiceImpl;
import com.ioc.support.ApplicationContext;
import com.ioc.support.DefaultApplicationContext;

/**
 * @author wanchongyang
 * @date 2018/7/21
 */
public class IOCTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new DefaultApplicationContext(new String[] {"com.ioc"});
        UserService userServiceByName = (UserService) applicationContext.getBean("userService");
        UserService userServiceByClazz = applicationContext.getBean(UserServiceImpl.class);
        System.out.println("====================singleton===========================");
        System.out.println("userServiceByName == userServiceByClazz:" + (userServiceByName == userServiceByClazz));
        userServiceByName.study();
        userServiceByClazz.study();
        System.out.println("====================prototype===========================");
        EnterpriseService enterpriseServiceByName = (EnterpriseService) applicationContext.getBean("enterpriseServiceImpl");
        EnterpriseService enterpriseServiceByClazz = applicationContext.getBean(EnterpriseServiceImpl.class);
        System.out.println("enterpriseServiceByName == enterpriseServiceByClazz:" + (enterpriseServiceByName == enterpriseServiceByClazz));
        enterpriseServiceByName.manage();
        enterpriseServiceByClazz.manage();
        System.out.println("=========================================================");
    }
}
