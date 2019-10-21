package com.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wanchongyang
 * @date 2019/9/23 10:07 上午
 */
public class StringHelpers {
    /**
     * 匹配_加任意一个字符
     */
    private static final Pattern UNDER_LINE_PATTERN = Pattern.compile("_(\\w)");


    /***
     * 下划线命名转为驼峰命名
     *
     * @param source
     *        下划线命名的字符串
     */

    public static String underlineToHump(String source) {
        StringBuilder result = new StringBuilder();
        String[] a = source.split("_");
        for (String s : a) {
            if (result.length() == 0) {
                result.append(s.toLowerCase());
            } else {
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }


    /***
     * 驼峰命名转为下划线命名
     *
     * @param source
     *        驼峰命名的字符串
     */

    public static String humpToUnderline(String source) {
        StringBuilder sb = new StringBuilder(source);
        //定位
        int temp = 0;
        for (int i = 0; i < source.length(); i++) {
            if (Character.isUpperCase(source.charAt(i))) {
                sb.insert(i + temp, "_");
                temp += 1;
            }
        }
        return sb.toString().toUpperCase();
    }

    /**
     * Create by lrt<br/>
     * Date：2018/10/10
     * Description： 下划线转为驼峰格式
     *
     * @param source 原字符串
     * @return java.lang.String 返回转换后的驼峰格式字符串
     */
    public static String underLineToCamel(String source) {
        //用Pattern类的matcher()方法生成一个Matcher对象
        Matcher matcher = UNDER_LINE_PATTERN.matcher(source);

        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            matcher.appendReplacement(result, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(result);
        return result.toString();
    }
}
