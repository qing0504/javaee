package com.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * List工具类
 * @author wanchongyang
 * @date 2018/11/20 10:51 PM
 */
public class ListUtil {
    public static <T> List<List<T>> partition(List<T> list, int size) {
        Objects.requireNonNull(list);
        if (size < 1) {
            throw new IllegalArgumentException("argument size must greater than 0");
        }

        List<List<T>> resultList = new ArrayList<>();
        if (list.size() <= size) {
            resultList.add(list);
            return resultList;
        }

        List<T> tempList = new ArrayList<>(size);
        int totalCount = list.size();
        for (int i = 1; i <= totalCount; i++) {
            if (i%size == 0 || i == totalCount) {
                tempList.add(list.get(i - 1));
                resultList.add(tempList);
                tempList = new ArrayList<>(size);
                continue;
            }

            tempList.add(list.get(i - 1));
        }

        return resultList;
    }
}
