package com.java8.reference;

import java.util.*;

/**
 * java8方法引用
 * @author wanchongyang
 * @date 2018/4/5
 */
public class SortMethodReferenceTest {
    public static void main(String[] args) {
        List<String> sortList = Arrays.asList("Jone", "angel", "martin", "Boe");
        // method one
        Collections.sort(sortList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });

        sortList.stream().forEach(System.out::println);
        System.out.println("=======================");

        // method two
        sortList = Arrays.asList("Jone", "angel", "martin", "Boe");
        Collections.sort(sortList, (o1, o2) -> o1.compareToIgnoreCase(o2));
        sortList.stream().forEach(System.out::println);
        System.out.println("=======================");

        // method three
        // 引用某个类型的任意对象的实例方法 => ContainingType::methodName
        sortList = Arrays.asList("Jone", "angel", "martin", "Boe");
        Collections.sort(sortList, String::compareToIgnoreCase);
        sortList.stream().forEach(System.out::println);
        System.out.println("=======================");
    }
}
