package com.java8.functioninterface;

import java.util.function.Function;

/**
 * @author wanchongyang
 * @date 2018/4/19 下午4:11
 */
public class FunctionDemo {
    public static void main(String[] args) {
        Function<Integer, String> function = (a) -> a + "";
        double result = function.andThen((s) -> Double.parseDouble(s) + 3.55).apply(3);
        System.out.println(result);
    }


}
