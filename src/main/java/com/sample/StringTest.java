package com.sample;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * @author wanchongyang
 * @date 2022/2/16 10:42 PM
 */
public class StringTest {
    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix(strs));
        System.out.println(longestCommonPrefix2(strs));
    }

    private static String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0){
            return "";
        }

        String minStr = Arrays.stream(strs).min(Comparator.comparingInt(String::length)).stream().findFirst().get();
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (char ele : minStr.toCharArray()) {
            boolean flag = true;
            for (String origin : strs) {
                if (!Objects.equals(ele, origin.toCharArray()[index])) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                sb.append(ele);
                index++;
            } else {
                break;
            }
        }

        return sb.length() > 0 ? sb.toString() : "";
    }

    private static String longestCommonPrefix2(String[] strs) {
        if(strs == null || strs.length == 0){
            return "";
        }
        // 依次遍历字符串数组，更新最长公共前缀
        int length = strs.length;
        String prefix = strs[0];
        for(int i = 1; i < length; i++){
            prefix = calPrefix(prefix, strs[i]);
            if(prefix.length() == 0){
                return prefix;
            }
        }
        return prefix;
    }

    private static String calPrefix(String str1, String str2){
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        for(int i = 0; i < length; i++){
            if(str1.charAt(i) != str2.charAt(i)){
                break;
            }
            index++;
        }
        return str1.substring(0, index);
    }

    private static String longestCommonPrefix3(String[] strs) {
        if(strs == null || strs.length == 0){
            return "";
        }
        // 纵向扫描：遍历第一个字符串的字符，并与其余字符串相应位置的字符比较
        for(int i = 0; i < strs[0].length(); i++){
            char c = strs[0].charAt(i);
            for(int j = 0; j < strs.length; j++){
                if(i == strs[j].length() || strs[j].charAt(i) != c){
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    private static String longestCommonPrefix4(String[] strs) {
        if(strs == null || strs.length == 0){
            return "";
        }
        // 分治
        return calPrefix(strs, 0, strs.length - 1);
    }

    private static String calPrefix(String[] strs, int start, int end){
        if(start == end){
            return strs[start];
        }
        int mid = start + (end - start) / 2;
        String left = calPrefix(strs, start, mid);
        String right = calPrefix(strs, mid+1, end);
        return doCal(left, right);
    }

    private static String doCal(String str1, String str2){
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        for(int i = 0; i < length; i++){
            if(str1.charAt(i) != str2.charAt(i)){
                break;
            }
            index++;
        }
        return str1.substring(0, index);
    }

    private static String longestCommonPrefix5(String[] strs) {
        if(strs == null || strs.length == 0){
            return "";
        }
        // 二分查找
        // 最短字符串的字符数 minLength
        int minLength = Integer.MAX_VALUE;
        for(int i = 0; i < strs.length; i++){
            minLength = Math.min(minLength, strs[i].length());
        }
        // 在 0 - minLength 区间内进行二分查找
        int start = 0, end = minLength;
        while(start < end){
            int mid = (end - start + 1) / 2 + start;
            if(isPrefix(strs, mid)){
                start = mid;
            }else{
                end = mid - 1;
            }
        }
        return strs[0].substring(0, start);
    }

    private static boolean isPrefix(String[] strs, int length){
        String str0 = strs[0].substring(0, length);
        for(int i = 0; i < strs.length; i++){
            String str = strs[i];
            for(int j = 0; j < length; j++){
                if(str0.charAt(j) != str.charAt(j)){
                    return false;
                }
            }
        }
        return true;
    }
}
