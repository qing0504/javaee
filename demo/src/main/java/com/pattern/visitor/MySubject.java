package com.pattern.visitor;

/**
 * Created by wanchongyang on 2017/10/11.
 */
public class MySubject implements Subject {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getSubject() {
        return "love";
    }
}
