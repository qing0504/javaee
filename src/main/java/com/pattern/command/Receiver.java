package com.pattern.command;

/**
 * 接收者(Receiver)角色：负责具体实施和执行一个请求。任何一个类都可以成为接收者，实施和执行请求的方法叫做行动方法。
 * Created by wanchongyang on 2017/10/11.
 */
public class Receiver {
    public void action(){
        System.out.println("command received!");
    }
}
