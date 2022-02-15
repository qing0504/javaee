package com.algorithm;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author wanchongyang
 * @date 2022/1/20 2:11 PM
 */
public class SearchMain {
    public static void main(String[] args) {
        // part1
        int[] nums = new int[]{1, 2, 3, 7, 4, 2, -10, -8};
        // 左右两侧数据和相等索引位置
        System.out.println("part1:" + pivotIndex(nums));

        // part2（插入搜索）
        int[] nums2 = new int[]{1, 2, 5, 7, 9, 10};
        System.out.println("part2:" + searchInsert(nums2, 7));

        // part3：merge
        int[][] part3 = new int[][]{{1, 5}, {2, 8}, {15, 18}, {5, 13}};
        System.out.println("part3:" + JSON.toJSONString(merge(part3)));
    }

    private static int pivotIndex(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        int leftSum = 0;
        for (int j = 0; j < nums.length; j++) {
            int rightSum = sum - leftSum - nums[j];
            if (leftSum == rightSum) {
                return j;
            }

            leftSum += nums[j];
        }

        return -1;
    }

    private static int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                return middle;
            }
        }

        return left;
    }

    /**
     * merge
     * 贪心算法
     *
     * @param intervals
     * @return
     */
    private static int[][] merge(int[][] intervals) {
        int len = intervals.length;
        if (len < 2) {
            return intervals;
        }

        // 数组元素按左边界进行升序排序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        List<int[]> result = new ArrayList<>();
        result.add(intervals[0]);

        // 如果当前遍历到的区间的左端点 > 结果集中最后一个区间的右端点，说明它们没有交集，此时把区间添加到结果集；
        // 如果当前遍历到的区间的左端点 <= 结果集中最后一个区间的右端点，说明它们有交集，此时产生合并操作，即：对结果集中最后一个区间的右端点更新（取两个区间的最大值）。
        for (int i = 1; i < len; i++) {
            int[] currentVal = intervals[i];
            int[] last = result.get(result.size() - 1);
            if (last[1] < currentVal[0]) {
                result.add(currentVal);
            } else {
                last[1] = Math.max(last[1], currentVal[1]);
            }
        }

        return result.toArray(new int[result.size()][]);
    }
}
