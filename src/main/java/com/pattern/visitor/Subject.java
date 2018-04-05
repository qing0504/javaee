package com.pattern.visitor;

/**
 * Created by wanchongyang on 2017/10/11.
 */
public interface Subject {
    void accept(Visitor visitor);
    String getSubject();
}
