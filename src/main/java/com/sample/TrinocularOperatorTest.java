package com.sample;

/**
 * 三目运算符的坑
 * @author wanchongyang
 * @date 2019-06-03 17:47
 */
public class TrinocularOperatorTest {
    public static void main(String[] args) {
        Object value = null;
        double doubleVal = 3.0;
        long longVal = (long) doubleVal;
        value = (longVal == doubleVal) ? longVal : doubleVal;
        System.out.println("first========" + (value instanceof Long));
        System.out.println("first========" + (value instanceof Double));
        System.out.println("=================================");

        boolean flag = longVal == doubleVal;
        if (flag) {
            value = longVal;
        } else {
            value = doubleVal;
        }
        System.out.println("second========" + (value instanceof Long));
        System.out.println("second========" + (value instanceof Double));
    }
}
