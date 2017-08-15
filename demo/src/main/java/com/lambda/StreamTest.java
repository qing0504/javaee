package com.lambda;

import org.apache.commons.lang3.RandomStringUtils;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by wanchongyang on 2017/8/15.
 */
public class StreamTest {
    public static void main(String[] args) {
        List<String> test = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            test.add(RandomStringUtils.randomAlphabetic(1));
        }

        Instant begin = Instant.now();

        Map<String, List<String>> result = test.parallelStream()
                .collect(Collectors.groupingByConcurrent(s -> {
                            try {
                                TimeUnit.MILLISECONDS.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            return s;
                        },
                        () -> new ConcurrentHashMap<String, List<String>>(64),
                        Collectors.mapping(s -> s, Collectors.toList())
                ));

        Instant end = Instant.now();

        System.out.println("耗时：" + Duration.between(begin, end).toMillis() + "毫秒");
        result.forEach((k, v) -> {
            System.out.println(k + "=" + v.size());
        });
    }
}
