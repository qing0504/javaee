package com.java8.lambda;

/**
 * lambda编译分析
 * @author wanchongyang
 * @date 2018/4/15 下午10:18
 */
public class LambdaTranslationTest {

    @FunctionalInterface
    public interface Calculator<T extends Number, U extends Number, R extends Number> {
        R calc(T t, U u);
    }

    public static void main(String[] args) {
        Calculator<Integer, Double, Double> calculator = (x1, x2) -> x1 + x2;
        int num1 = 1;
        double num2 = 4.88;
        double result = calculator.calc(num1, num2);
        System.out.println(result);
        System.out.println("---------lambda----------");
        System.out.println(print(2.48, 4, (x1, x2) -> x1 + x2));
    }

    public static <T extends Number, U extends Number, R extends Number> R print(T t, U u, Calculator<T, U, R> calculator) {
        return calculator.calc(t, u);
    }
}
