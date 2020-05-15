package com.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 数字10进制与62进制互相转换
 * @author wanchongyang
 * @date 2020/5/4 10:55 下午
 */
public class ConversionUtil {
    private static final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int scale = 62;

    /**
     * 数字转62进制
     * @param num    Long 型数字
     * @param length 转换后的字符串长度，不足则左侧补0
     * @return 62进制字符串
     */
    public static String encode(long num, int length) {
        StringBuilder sb = new StringBuilder();
        int remainder;
        while (num > scale - 1) {
            //对 scale 进行求余，然后将余数追加至 sb 中，由于是从末位开始追加的，因此最后需要反转字符串
            remainder = Long.valueOf(num % scale).intValue();
            sb.append(chars.charAt(remainder));
            //除以进制数，获取下一个末尾数
            num = num / scale;
        }
        sb.append(chars.charAt(Long.valueOf(num).intValue()));
        String value = sb.reverse().toString();
        return StringUtils.leftPad(value, length, '0');
    }

    /**
     * 62进制转为数字
     * @param str 编码后的62进制字符串
     * @return 解码后的 10 进制数字
     */
    public static long decode(String str) {
        // 将 0 开头的字符串进行替换
        str = str.replaceAll("^0*", "");
        long value = 0;
        char tempChar;
        int tempCharValue;
        for (int i = 0; i < str.length(); i++) {
            // 获取字符
            tempChar = str.charAt(i);
            // 单字符值
            tempCharValue = chars.indexOf(tempChar);
            // 单字符值在进制规则下表示的值
            value += (long) (tempCharValue * Math.pow(scale, str.length() - i - 1));
        }
        return value;
    }

    public static void main(String[] args) {
        String encodeStr = encode(1000L, 6);
        System.out.println("62进制：" + encodeStr);
        System.out.println("10进制：" + decode(encodeStr));

    }
}
