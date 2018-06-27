package com.lambda;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author wanchongyang
 * @date 2018/5/3 下午6:54
 */
public class CollectorsTest {
    public static void main(String[] args) {
        List<Account> accounts = Account.build(10);
        accounts.forEach(Account::print);

        // K:Account.getId   V:Account.getUserName
        Collector<Account, Map<Long, String>, Map<Long, String>> collector = Collector.of(
//        Collector<Account, ?, Map<Long, String>> collector = Collector.of(
                () -> {
//                HashMap::new,
                    System.out.println("===============supplier==================");
                    return new HashMap<>(16);
                },
                (map, t) -> {
                    System.out.println("===============accumulator==================");
                    map.put(t.getId(), t.getUserName());
                },
                (left, right) -> {
                    System.out.println("===============combiner==================");
                    System.out.println(Thread.currentThread().getName() + "=======left : " + left.toString() + ", right : " + right.toString());
                    left.putAll(right);
                    return left;
                },
                m -> {
                    System.out.println("===============finisher==================");
                    return m;
                }
        );

        System.out.println("==============My Collector=====================");
        // java8-tutorial  Streams10
        // 并行parallelism流
       Map<Long, String> map = accounts.parallelStream().collect(collector);
        // Map<Long, String> map = accounts.stream().collect(collector);
        map.forEach((k, v) -> System.out.println(k + " : " + v));

        System.out.println("==============equivalent to My Collector =====================");
        Map<Long, String> container = collector.supplier().get();
        for (Account t : accounts) {
            collector.accumulator().accept(container, t);
        }
        Map<Long, String> finisherResult = collector.finisher().apply(container);
        finisherResult.forEach((k, v) -> System.out.println(k + " : " + v));

        System.out.println("==============Collectors.toMap=====================");
        accounts.stream().collect(Collectors.toMap(Account::getId, Account::getUserName))
                .forEach((k, v) -> System.out.println(k + " : " + v));

    }
}
