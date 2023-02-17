package com.common.utils;

import java.util.Arrays;

/**
 * Java实现的排序类
 * Created by Martin on 2017/3/2.
 */
public class NumberSortUtil {

    //私有构造方法，禁止实例化
    private NumberSortUtil() {
        super();
    }

    //冒泡法排序
    public static void bubbleSort(int[] numbers) {
        int temp; // 记录临时中间值
        int size = numbers.length; // 数组大小
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (numbers[i] < numbers[j]) { // 交换两数的位置
                    temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }
    }

    public static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length; //定义target在左闭右开的区间里，即[left, right)
        while (left < right) {    //因为left = right的时候，在[left, right)区间上无意义
            int middle = left + ((right - left) / 2);
            if (nums[middle] > target) {
                right = middle; //target 在左区间，在[left, middle)中
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        // 没找到就返回-1
        return -1;
    }

    public static int binarySearch2(int[] nums, int target) //nums是数组，target是需要查找的值
    {
        int size = nums.length;
        int left = 0;
        int right = size - 1;    // 定义了target在左闭右闭的区间内，[left, right]
        while (left <= right) {    //当left == right时，区间[left, right]仍然有效
            int middle = left + ((right - left) / 2);//等同于 (left + right) / 2，防止溢出
            if (nums[middle] > target) {
                right = middle - 1;    //target在左区间，所以[left, middle - 1]
            } else if (nums[middle] < target) {
                left = middle + 1;    //target在右区间，所以[middle + 1, right]
            } else {    //既不在左边，也不在右边，那就是找到答案了
                return middle;
            }
        }
        //没有找到目标值
        return -1;
    }

    //快速排序
    public static void quickSort(int[] numbers, int start, int end) {
        if (start < end) {
            int base = numbers[start]; // 选定的基准值（第一个数值作为基准值）
            int temp; // 记录临时中间值
            int i = start, j = end;
            do {
                while ((numbers[i] < base) && (i < end))
                    i++;
                while ((numbers[j] > base) && (j > start))
                    j--;
                if (i <= j) {
                    temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                    i++;
                    j--;
                }
            } while (i <= j);
            if (start < j)
                quickSort(numbers, start, j);
            if (end > i)
                quickSort(numbers, i, end);
        }
    }

    //选择排序
    public static void selectSort(int[] numbers) {
        int size = numbers.length, temp;
        for (int i = 0; i < size; i++) {
            int k = i;
            for (int j = size - 1; j > i; j--) {
                if (numbers[j] < numbers[k])
                    k = j;
            }
            temp = numbers[i];
            numbers[i] = numbers[k];
            numbers[k] = temp;
        }
    }

    //插入排序
    // @param numbers
    public static void insertSort(int[] numbers) {
        int size = numbers.length, temp, j;
        for (int i = 1; i < size; i++) {
            temp = numbers[i];
            for (j = i; j > 0 && temp < numbers[j - 1]; j--)
                numbers[j] = numbers[j - 1];
            numbers[j] = temp;
        }
    }

    /**
     * 创建堆，
     *
     * @param arr 待排序列
     */
    public static void heapSort(int[] arr) {
        //创建堆
        for (int i = (arr.length - 1) / 2; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr, i, arr.length);
        }

        //调整堆结构+交换堆顶元素与末尾元素
        for (int i = arr.length - 1; i > 0; i--) {
            //将堆顶元素与末尾元素进行交换
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;

            //重新对堆进行调整
            adjustHeap(arr, 0, i);
        }
    }

    /**
     * 调整堆
     *
     * @param arr    待排序列
     * @param parent 父节点
     * @param length 待排序列尾元素索引
     */
    private static void adjustHeap(int[] arr, int parent, int length) {
        //将temp作为父节点
        int temp = arr[parent];
        //左孩子
        int lChild = 2 * parent + 1;

        while (lChild < length) {
            //右孩子
            int rChild = lChild + 1;
            // 如果有右孩子结点，并且右孩子结点的值大于左孩子结点，则选取右孩子结点
            if (rChild < length && arr[lChild] < arr[rChild]) {
                lChild++;
            }

            // 如果父结点的值已经大于孩子结点的值，则直接结束
            if (temp >= arr[lChild]) {
                break;
            }

            // 把孩子结点的值赋给父结点
            arr[parent] = arr[lChild];

            //选取孩子结点的左孩子结点,继续向下筛选
            parent = lChild;
            lChild = 2 * lChild + 1;
        }
        arr[parent] = temp;
    }

    //归并排序
    public static void mergeSort(int[] numbers, int left, int right) {
        int t = 1;// 每组元素个数
        int size = right - left + 1;
        while (t < size) {
            int s = t;// 本次循环每组元素个数
            t = 2 * s;
            int i = left;
            while (i + (t - 1) < size) {
                merge(numbers, i, i + (s - 1), i + (t - 1));
                i += t;
            }
            if (i + (s - 1) < right)
                merge(numbers, i, i + (s - 1), right);
        }
    }

    //归并算法实现
    private static void merge(int[] data, int p, int q, int r) {
        int[] B = new int[data.length];
        int s = p;
        int t = q + 1;
        int k = p;
        while (s <= q && t <= r) {
            if (data[s] <= data[t]) {
                B[k] = data[s];
                s++;
            } else {
                B[k] = data[t];
                t++;
            }
            k++;
        }
        if (s == q + 1)
            B[k++] = data[t++];
        else
            B[k++] = data[s++];
        for (int i = p; i <= r; i++)
            data[i] = B[i];
    }

    public static void main(String[] args) {
        int[] nums = {15, 23, 38, 47, 55, 62, 88, 95, 102, 123};
        // System.out.println(binarySearch(nums, 95));
        System.out.println(binarySearch2(nums, 95));

        // 堆排序
        int[] heapNums = {7, 10, 13, 15, 4, 20, 19, 8};
        heapSort(heapNums);
        System.out.println(Arrays.toString(heapNums));
    }
}