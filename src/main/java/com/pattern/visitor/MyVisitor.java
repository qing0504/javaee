package com.pattern.visitor;

/**
 * Created by wanchongyang on 2017/10/11.
 */
public class MyVisitor implements Visitor {

    @Override
    public void visit(Subject sub) {
        System.out.println("visit the subject："+sub.getSubject());
    }
}
