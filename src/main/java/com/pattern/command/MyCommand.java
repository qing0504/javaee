package com.pattern.command;

/**
 * 具体命令(ConcreteCommand)角色：定义一个接收者和行为之间的弱耦合；
 * 实现execute()方法，负责调用接收者的相应操作。execute()方法通常叫做执行方法。
 * Created by wanchongyang on 2017/10/11.
 */
public class MyCommand implements Command {

    private Receiver receiver;

    public MyCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void exe() {
        receiver.action();
    }
}
