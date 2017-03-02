package com.sample;

import com.common.utils.NumberSortUtil;

/**
 * Created by Martin on 2017/3/2.
 */
public class NumberSortDemo {
    public static void main(String[] args) {
        int[] nums = {12, 4, 9, 10, 23, 1};

        //冒泡排序
        //NumberSortUtil.bubbleSort(nums);

        //选择排序
        //NumberSortUtil.selectSort(nums);

        //快速排序
        NumberSortUtil.quickSort(nums, 1, 3);

        //插入排序
        //NumberSortUtil.insertSort(nums);

        //归并排序
        //NumberSortUtil.mergeSort(nums, 1, 5);

        for(int num : nums) {
            System.out.print(num + "\t");
        }
    }
}
