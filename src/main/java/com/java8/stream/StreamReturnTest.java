package com.java8.stream;

import com.google.common.collect.Lists;

/**
 * @author wanchongyang
 * @date 2020/2/13 4:29 下午
 */
public class StreamReturnTest {
    public static void main(String[] args) {
        Lists.newArrayList(1, 2, 3, 4, 5).forEach(e -> {
            System.out.println(e);
            if (e == 3) {
                return;
            }
            System.out.println("after:" + e);
        });
    }
}
