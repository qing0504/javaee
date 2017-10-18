package com.sample;

/**
 * @author wanchongyang
 * @date 2017/10/18
 */
public class ReverseTest {
    public static void main(String[] args) {
        String str1 = null;
        System.out.println(reverse(str1));
        String str2 = "a";
        System.out.println(reverse(str2));
        String str3 = "ab";
        System.out.println(reverse(str3));
        String str4 = "abcdefghijkl";
        System.out.println(reverse(str4));
    }

    private static String reverse(String origin) {
        if (origin == null || origin.length() <= 1) {
            return origin;
        }

        return reverse(origin.substring(1)) + origin.charAt(0);
    }
}
