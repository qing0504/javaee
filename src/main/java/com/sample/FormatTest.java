package com.sample;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wanchongyang on 2017/10/15.
 */
public class FormatTest {
    /**
     * SimpleDateFormat线程安全写法
     */
    private static final ThreadLocal<DateFormat> DATE_FORMAT_THREAD_LOCAL = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static void main(String[] args) {
        DateFormat dateFormat = DATE_FORMAT_THREAD_LOCAL.get();
        String dateStr = dateFormat.format(new Date());
        System.out.println(dateStr);

        String pattern = "%01d";
        System.out.println(String.format(pattern, 0));
        System.out.println(String.format(pattern, 39));
    }
}
