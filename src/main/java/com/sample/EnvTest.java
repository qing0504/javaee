package com.sample;

import java.util.Map;

/**
 * @author wanchongyang
 * @date 2019-06-18 14:38
 */
public class EnvTest {
    public static void main(String[] args) {
        Map<String, String> map = System.getenv();
        map.forEach((k, v) -> System.out.println(k + "=" + v));
    }
}
