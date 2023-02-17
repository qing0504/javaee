package com.algorithm;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 题目描述 有 20 个数组，每个数组有 500 个元素，并且有序排列。如何在这 20*500 个数中找出前 500 的
 * 数？
 * 解答思路
 * 对于 TopK 问题，最常用的方法是使用堆排序。对本题而言，假设数组降序排列，可以采用以
 * 下方法：
 * 首先建立大顶堆，堆的大小为数组的个数，即为 20，把每个数组最大的值存到堆中。
 * 接着删除堆顶元素，保存到另一个大小为 500 的数组中，然后向大顶堆插入删除的元素所在数
 * 组的下一个元素。
 * 重复上面的步骤，直到删除完第 500 个元素，也即找出了最大的前 500 个数。
 *
 * @author wanchongyang
 * @date 2022/11/1 23:08
 */
public class TopDataMain {
    public static int[] getTop(int[][] data) {
        int rowSize = data.length;
        int columnSize = data[0].length; // 创建一个columnSize大小的数组，存放结果
        int[] result = new int[columnSize];
        PriorityQueue<DataWithSource> maxHeap = new PriorityQueue<>();
        for (int i = 0; i < rowSize; ++i) { // 将每个数组的最大一个元素放入堆中
            DataWithSource d = new DataWithSource(data[i][0], i, 0);
            maxHeap.add(d);
        }
        int num = 0;
        while (num < columnSize) { // 删除堆顶元素
            DataWithSource d = maxHeap.poll();
            result[num++] = d.getValue();
            if (num >= columnSize) {
                break;
            }
            d.setValue(data[d.getSource()][d.getIndex() + 1]);
            d.setIndex(d.getIndex() + 1);
            maxHeap.add(d);
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] data = {{29, 17, 14, 2, 1}, {19, 17, 16, 15, 6}, {30, 25, 20, 14, 5}};
        int[] top = getTop(data);
        System.out.println(Arrays.toString(top)); // [30, 29, 25, 20, 19]
    }
}
