package com.algorithm;

import java.util.Arrays;

/**
 * @author wanchongyang
 * @date 2022/10/17 22:03
 */
public class KMPMain {
    public static void main(String[] args) {
        String origin = "AABBCBBABBCACCD";
        String pattern = "BBABBCAC";
        System.out.println(Arrays.toString(next(pattern)));
        System.out.println(kmp(origin, pattern));
    }

    public static int kmp(String origin, String pattern) {
        int i = 0;
        int j = 0;
        int[] next = next(origin);
        int lt = origin.length();
        int ls = pattern.length();
        while (i < lt && j < ls) {
            if (j == -1 || origin.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }

        if (j >= ls) {
            return i - j + 1;
        } else {
            return -1;
        }

    }

    public static int[] next(String pattern) {
        int i = 0;
        int j = -1;
        char[] s = pattern.toCharArray();
        int ls = s.length;
        int[] next = new int[ls];
        next[0] = -1;
        while (i < ls - 1) {
            if (j == -1 || s[i] == s[j]) {
                j++;
                i++;
                if (s[i] == s[j]) {
                    next[i] = next[j];
                } else {
                    next[i] = j;
                }
            } else {
                j = next[j];
            }
        }
        return next;
    }
}
