package com.pattern.interpreter;

/**
 * Created by wanchongyang on 2017/10/11.
 */
public class Plus implements Expression {

    @Override
    public int interpret(Context context) {
        return context.getNum1()+context.getNum2();
    }
}
