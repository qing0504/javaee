package com.sample;

/**
 * @author wanchongyang
 * @date 2018/11/11 8:50 PM
 */
public class IntTest {
    public static void main(String[] args) {
        int min = Integer.MIN_VALUE;
        // 10000000 00000000 00000000 00000000
        System.out.println(Integer.toBinaryString(min));

        long minMinusOne = min - 1;
        System.out.println(minMinusOne);

        int max = Integer.MAX_VALUE;
        // 01111111 11111111 11111111 11111111
        System.out.println(Integer.toBinaryString(max));

        long maxPlusOne = max + 1;
        System.out.println(maxPlusOne);
    }
}
