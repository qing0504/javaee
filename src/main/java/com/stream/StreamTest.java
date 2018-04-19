package com.stream;

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
 * 并行流与串行流效率比较
 * 影响并行流的因素：
 * 1、数据大小
 * 2、源数据结构：分割越容易越好，ArrayList、数组比较好，hashSet、treeSet次之，linked最差
 * 3、装箱：处理基本类型比处理装箱类型要快
 * 4、核的数量
 * 5、单元处理开销
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
