package com.pattern.mediator;

/**
 * Created by wanchongyang on 2017/10/11.
 */
public class User1 extends User {

    public User1(Mediator mediator){
        super(mediator);
    }

    @Override
    public void work() {
        System.out.println("user1 exe!");
    }
}
