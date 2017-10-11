package com.pattern.command;

/**
 * 客户端(Client)角色：创建一个具体命令(ConcreteCommand)对象并确定其接收者。
 * Created by wanchongyang on 2017/10/11.
 */
public class Client {
    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        Command cmd = new MyCommand(receiver);
        Invoker invoker = new Invoker(cmd);
        invoker.action();
    }
}
