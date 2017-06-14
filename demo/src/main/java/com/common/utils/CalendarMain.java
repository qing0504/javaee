package com.common.utils;

/**
 * Created by wanchongyang on 2017/6/14.
 */
public class CalendarMain {
    public static void main(String[] args) throws Exception {
        System.out.println(CalendarUtil.lunarToSolar("20170520", false));
        System.out.println(CalendarUtil.solarToLunar("20170614"));

        System.out.println(CalendarUtil2.lunarToSolar("20170520"));
        System.out.println(CalendarUtil2.solarToLunar("20170614"));
    }
}
