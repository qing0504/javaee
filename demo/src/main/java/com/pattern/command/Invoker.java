package com.pattern.command;

/**
 * 请求者(Invoker)角色：负责调用命令对象执行请求，相关的方法叫做行动方法。
 * Created by wanchongyang on 2017/10/11.
 */
public class Invoker {
    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void action(){
        command.exe();
    }
}
