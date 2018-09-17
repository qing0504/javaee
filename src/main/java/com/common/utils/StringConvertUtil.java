package com.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wanchongyang
 * @date 2018/7/21
 */
public class StringConvertUtil {
    private static Pattern CAMEL_PATTERN = Pattern.compile("([A-Za-z\\d]+)(_)?");
    private static Pattern UNDERLINEL_PATTERN = Pattern.compile("[A-Z]([a-z\\d]+)?");

    /**
     * 首字母转小写
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 首字母转大写
     */
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 下划线转驼峰法(默认小驼峰)
     *
     * @param line
     *            源字符串
     * @param smallCamel
     *            大小驼峰,是否为小驼峰(驼峰，第一个字符是大写还是小写)
     * @return 转换后的字符串
     */
    public static String underline2Camel(String line, boolean... smallCamel) {
        if (line == null || "".equals(line)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        Matcher matcher = CAMEL_PATTERN.matcher(line);
        //匹配正则表达式
        while (matcher.find()) {
            String word = matcher.group();
            //当是true 或则是空的情况
            if((smallCamel.length ==0 || smallCamel[0] ) && matcher.start()==0){
                sb.append(Character.toLowerCase(word.charAt(0)));
            }else{
                sb.append(Character.toUpperCase(word.charAt(0)));
            }

            int index = word.lastIndexOf('_');
            if (index > 0) {
                sb.append(word.substring(1, index).toLowerCase());
            } else {
                sb.append(word.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }

    /**
     * 驼峰法转下划线
     *
     * @param line
     *            源字符串
     * @return 转换后的字符串
     */
    public static String camel2Underline(String line) {
        if (line == null || "".equals(line)) {
            return "";
        }
        line = String.valueOf(line.charAt(0)).toUpperCase().concat(line.substring(1));
        StringBuffer sb = new StringBuffer();
        Matcher matcher = UNDERLINEL_PATTERN.matcher(line);
        while (matcher.find()) {
            String word = matcher.group();
            sb.append(word.toLowerCase());
            sb.append(matcher.end() == line.length() ? "" : "_");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(camel2Underline("userName"));
        // 小驼峰
        System.out.println(underline2Camel("user_code"));
        System.out.println(underline2Camel("user_code", true));
        // 大驼峰
        System.out.println(underline2Camel("user_code", false));
    }
}
