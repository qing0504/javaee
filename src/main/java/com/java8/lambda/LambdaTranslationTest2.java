package com.java8.lambda;

/**
 * lambda编译分析
 * @author wanchongyang
 * @date 2018/4/15 下午10:18
 */
public class LambdaTranslationTest2 {

    @FunctionalInterface
    public interface Calculator<T extends Number, U extends Number, R extends Number> {
        R calc(T t, U u);
    }

    private static double lambda$main$0(double d1, int i1) {
        return d1 + i1;
    }

    final class Lambda$14 implements Calculator<Double, Integer, Double> {

        @Override
        public Double calc(Double aDouble, Integer integer) {
            return lambda$main$0(aDouble, integer);
        }
    }

    public static void main(String[] args) {
//        System.out.println(print(2.48, 4, (x1, x2) -> x1 + x2));
        System.out.println(print(2.48, 4, new LambdaTranslationTest2().new Lambda$14()));
    }

    public static <T extends Number, U extends Number, R extends Number> R print(T t, U u, Calculator<T, U, R> calculator) {
        return calculator.calc(t, u);
    }
}
