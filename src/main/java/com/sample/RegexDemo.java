package com.sample;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Martin on 2017/2/15.
 */
public class RegexDemo {
    public static void main(String[] args) {
        //1，把手机号码15921510980转换成159****0980？
        String tel = "15921510980";
        tel = tel.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        System.out.println(tel);
        //2, 把字符串“zhangsanttttlisimmmmmwangwu”中重复3次以上的字母替换成“#”？
        String str = "zhangsanttttlisimmmmmwangwu";
        str = str.replaceAll("(.)\\1{3}", "#");
        System.out.println(str);
        //3，治口吃
        String str2 = "...我我我我我我我我我我我我我我我.要要要要要.学学学.编编编编编编............程程程程程程程............";
        str2 = str2.replaceAll("\\.+", "");
        System.out.println(str2);
        str2 = str2.replaceAll("(.)\\1+", "$1");
        System.out.println(str2);

        //1，获取字符串“da jia hao, ming tian bu fang jia!”中的连续三个字母的字符串？
        /**
         * 获取字符串
         * 1, 把正则表达式封装成Pattern对象;
         * 2, 用Pattern对象的matcher方法匹配字符串,获得Matcher对象;
         * 3, 用Matcher对象的find方法查找匹配的字符串;
         * 4, 用Matcher对象的group方法获取匹配的字符串.
         */
        String regex = "\\b[a-z]{3}\\b";
        String str3 = "da jia hao, ming tian bu fang jia!";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str3);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }

    }
}
