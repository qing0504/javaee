package com.algorithm.matrix;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author wanchongyang
 * @date 2022/2/15 10:27 AM
 */
public class MatrixMain {
    public static void main(String[] args) {
        // 旋转矩阵
        // 规律：对于矩阵中第 i 行的第 j 列元素，在旋转后，它出现在的第 j 行的倒数第 i 列 位置。
        int[][] matrix = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        // System.out.println(Arrays.deepToString(rotate(matrix)));

        // 零矩阵
        int[][] zeroMatrix = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        setZeroes(zeroMatrix);
        // System.out.println(Arrays.deepToString(zeroMatrix));

        // 矩阵对角线遍历
        int[][] diagonalMatrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(Arrays.toString(findDiagonalOrder(diagonalMatrix)));
    }

    /**
     * 方法一：使用辅助数组
     * 复杂度分析
     * <p>
     * 时间复杂度：O(N^2)，其中 N是 matrix 的边长。
     * <p>
     * 空间复杂度：O(N^2)。我们需要使用一个和 matrix 大小相同的辅助数组。
     *
     * @param matrix
     * @return
     */
    private static int[][] rotate(int[][] matrix) {
        int len = matrix.length;
        int[][] temp = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                temp[j][len - i - 1] = matrix[i][j];
            }
        }

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                matrix[i][j] = temp[i][j];
            }

            // System.arraycopy(temp[i], 0, matrix[i], 0, len);
        }

        return matrix;
    }

    /**
     * 方法二：原地旋转
     * 复杂度分析
     * <p>
     * 时间复杂度：O(N^2)，其中 N 是 matrix 的边长。我们需要枚举的子矩阵大小为 O(⌊n/2⌋×⌊(n+1)/2⌋)=O(N2) = O(N^2)。
     * <p>
     * 空间复杂度：O(1)。为原地旋转。
     *
     * @param matrix
     */
    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }

    /**
     * 方法三：翻转
     * 复杂度分析
     * <p>
     * 时间复杂度：O(N^2)，其中 N 是 matrix 的边长。对于每一次翻转操作，我们都需要枚举矩阵中一半的元素。
     * <p>
     * 空间复杂度：O(1)。为原地翻转得到的原地旋转。
     *
     * @param matrix
     */
    public void rotate3(int[][] matrix) {
        int n = matrix.length;
        // 水平翻转
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < n; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }
        // 主对角线翻转
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    /**
     * 零矩阵
     * 元素0 所在的行、列清零
     *
     * @param matrix
     */
    private static void setZeroes(int[][] matrix) {
        int rowLen = matrix[0].length;
        int colLen = matrix.length;
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();
        for (int i = 0; i < colLen; i++) {
            for (int j = 0; j < rowLen; j++) {
                if (matrix[i][j] == 0) {
                    cols.add(i);
                    rows.add(j);
                }
            }
        }

        // 行清零 && 列清零
        for (int i = 0; i < colLen; i++) {
            for (int j = 0; j < rowLen; j++) {
                if (cols.contains(i) || rows.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * 对角线遍历
     *
     * @param matrix
     * @return
     */
    private static int[] findDiagonalOrder(int[][] matrix) {
        // 参数校验
        if (matrix.length == 0) {
            return new int[0];
        }
        int depth = matrix.length;
        int width = matrix[0].length;
        // 遍历次数
        int count = depth + width - 1;
        int m = 0;
        int n = 0;
        // 出参
        int[] ret = new int[depth * width];
        int retIndex = 0;
        // 遍历次数
        for (int k = 0; k < count; k++) {
            if (k % 2 == 0) {
                // 从左到右往上遍历
                while (m >= 0 && n < width) {
                    ret[retIndex++] = matrix[m--][n++];
                }

                // 数组越界，计算下次遍历开始坐标
                if (n < width) {
                    m += 1;
                } else {
                    m += 2;
                    n -= 1;
                }
            } else {
                // 从右到左往下遍历
                while (m < depth && n >= 0) {
                    ret[retIndex++] = matrix[m++][n--];
                }
                // 数组越界，计算下次遍历开始坐标
                if (m < depth) {
                    n += 1;
                } else {
                    m -= 1;
                    n += 2;
                }
            }

        }

        return ret;
    }
}
