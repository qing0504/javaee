package com.stream;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author wanchongyang
 * @date 2018/4/17 上午9:33
 */
public class MapStreamTest {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>(16);
        for(int i = 1; i <= 10; i++) {
            map.put(i, UUID.randomUUID().toString());
        }

        map.forEach((k, v) -> System.out.println(k + " : " + v));
    }
}
