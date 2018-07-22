package com.ioc;

/**
 * @author wanchongyang
 * @date 2018/7/21
 */
@Component("userService")
public class UserServiceImpl implements UserService {
    @Override
    public void study() {
        System.out.println("good good study. day day up.");
    }
}
