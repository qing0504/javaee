package com.pattern.visitor;

/**
 * Created by wanchongyang on 2017/10/11.
 */
public class VisitorTest {
    public static void main(String[] args) {

        Visitor visitor = new MyVisitor();
        Subject sub = new MySubject();
        sub.accept(visitor);
    }
}
