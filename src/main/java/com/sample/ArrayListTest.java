package com.sample;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanchongyang
 * @date 2019-02-28 09:55
 */
public class ArrayListTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(16);
        list.add("Hello");
        list.add("W");
        list.add("O");
        list.add("R");
        list.add("W");
        list.add("W");
        System.out.println("before list:" + list);

        List<String> removeList = new ArrayList<>(8);
        removeList.add("W");
        removeList.add("L");
        removeList.add("Hello");
        removeList.add("S");

        System.out.println("before removeList:" + removeList);

        // 补集
        // list.removeAll(removeList);

        // 交集
        list.retainAll(removeList);
        System.out.println("after list:" + list);
        System.out.println("after removeList:" + removeList);

    }
}
