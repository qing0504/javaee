package com.pattern.template;

/**
 * Created by wanchongyang on 2017/10/11.
 */
public class TemplateTest {
    public static void main(String[] args) {
        String exp = "8+8";
        AbstractCalculator cal = new Plus();
        int result = cal.calculate(exp, "\\+");
        System.out.println(result);
    }
}
