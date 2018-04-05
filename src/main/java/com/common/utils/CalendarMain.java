package com.common.utils;

import java.util.Date;

/**
 * Created by wanchongyang on 2017/6/14.
 */
public class CalendarMain {
    public static void main(String[] args) throws Exception {
        //System.out.println(CalendarUtil.lunarToSolar("20170520", false));
        //System.out.println(CalendarUtil.solarToLunar("20170614"));
        //
        //System.out.println(CalendarUtil2.lunarToSolar("20170520"));
        //System.out.println(CalendarUtil2.solarToLunar("20170614"));

        Date currentDate = new Date();
        System.out.println(DateUtil.formatDateFull(currentDate));
        String solarDateStr = CalendarUtil2.lunarToSolar("20170515");
        System.out.println(solarDateStr);
        Date solarDate = DateUtil.parse(solarDateStr, DateUtil.DATE_JFP_STR);
        int dayInterval = DateUtil.getDaySpace(currentDate, solarDate);
        System.out.println(dayInterval);

    }
}
