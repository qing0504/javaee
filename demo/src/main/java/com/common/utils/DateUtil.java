package com.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    /**
     * 定义常量
     **/
    public static final String DATE_JFP_STR = "yyyyMMdd";
    public static final String DATE_FULL_STR = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_SMALL_STR = "yyyy-MM-dd";
    public static final String DATE_KEY_STR = "yyMMddHHmmss";

    /**
     * 获取两个时间间隔天数
     *
     * @param d1
     * @param d2
     * @return
     */
    public static final int getDaySpace(Date d1, Date d2) {
        Calendar calst = Calendar.getInstance();
        Calendar caled = Calendar.getInstance();
        calst.setTime(d1);
        caled.setTime(d2);
        // 设置时间为0时
        calst.set(Calendar.HOUR_OF_DAY, 0);
        calst.set(Calendar.MINUTE, 0);
        calst.set(Calendar.SECOND, 0);
        caled.set(Calendar.HOUR_OF_DAY, 0);
        caled.set(Calendar.MINUTE, 0);
        caled.set(Calendar.SECOND, 0);
        // 得到两个日期相差的天数
        int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst.getTime().getTime() / 1000)) / 3600 / 24;
        return days;
    }

    /**
     * 获取指定日期一年以后日期(提前一天)
     *
     * @param beginDay
     * @return
     */
    public static final Date getDayNextOneYear(Date beginDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginDay);
        calendar.add(Calendar.YEAR, 1);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return calendar.getTime();
    }

    /**
     * 获取指定日期一季度以后日期(提前一天)
     *
     * @param beginDay
     * @return
     */
    public static final Date getDayNextOneSeason(Date beginDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginDay);
        calendar.add(Calendar.MONTH, 3);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    /**
     * 获取指定日期一个月以后日期(提前一天)
     *
     * @param beginDay
     * @return
     */
    public static final Date getDayNextMonth(Date beginDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginDay);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    /**
     * 获取下个月的今天
     *
     * @param beginDay
     * @return
     */
    public static final Date getSameDayNextMonth(Date beginDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginDay);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取指定前一个月的日期
     *
     * @param beginDay
     * @return
     */
    public static final Date getDayBeforeOneMonth(Date beginDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginDay);
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
    }

    /**
     * 获取指定日期前一个月第一天
     *
     * @param beginDay
     * @return
     */
    public static final Date getFirstDayOfBeforeMonth(Date beginDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginDay);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取指定日期前一个月最后一天
     *
     * @param beginDay
     * @return
     */
    public static final Date getLastDayOfBeforeMonth(Date beginDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    /**
     * 获取当月第一天
     *
     * @param beginDay
     * @return
     */
    public static final Date getFirstDayOfCurrentMonth(Date beginDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginDay);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取当月最后一天
     *
     * @param beginDay
     * @return
     */
    public static final Date getLastDayOfCurrentMonth(Date beginDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }


    /**
     * 使用用户格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return
     */
    public static Date parse(String strDate, String pattern) {
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(strDate, pattern);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 使用预设格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @return
     */
    public static Date parseDateFull(String strDate) {
        return parse(strDate, DATE_FULL_STR);
    }

    /**
     * 使用预设格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @return
     */
    public static Date parseDateSmall(String strDate) {
        return parse(strDate, DATE_SMALL_STR);
    }


    /**
     * 获取当天的时间23时59分钟59秒
     *
     * @param date
     * @return
     */
    public static Date getEndDateOfNowDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 获取当天的时间00时00分钟00秒
     *
     * @param date
     * @return
     */
    public static Date getStartDateOfNowDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 使用预设格式提取日期字符串
     *
     * @param date
     * @return
     */
    public static String formatDateFull(Date date) {
        return DateFormatUtils.format(date, DATE_FULL_STR);

    }

    /**
     * 使用预设格式提取日期字符串
     *
     * @param date
     * @return
     */
    public static String formatDateJfp(Date date) {
        return DateFormatUtils.format(date, DATE_JFP_STR);

    }


    /**
     * 获取字符串日期的当天开始时间
     *
     * @param strDate 2016-11-22 17:56:24
     * @return
     */
    public static String getCurrDateStartStr(String strDate) {

        Date dateFull = parseDateFull(strDate);
        if (null == dateFull) {
            Date dateSmall = parseDateSmall(strDate);
            if (null == dateSmall) {
                return null;
            } else {
                return formatDateFull(dateSmall);
            }
        } else {
            return strDate;
        }
    }

    /**
     * 获取字符串日期的当天结束时间
     *
     * @param strDate 2016-11-22 17:56:24
     * @return
     */
    public static String getCurrDateEndStr(String strDate) {
        Date dateFull = parseDateFull(strDate);
        if (null == dateFull) {
            Date dateSmall = parseDateSmall(strDate);
            if (null == dateSmall) {
                return null;
            } else {
                return formatDateFull(getEndDateOfNowDay(dateSmall));
            }
        } else {
            return strDate;
        }
    }


    public static String getFormateDateStr(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date day0 = cal.getTime();
        cal.add(Calendar.DATE, -1);
        Date day1 = cal.getTime();
        cal.add(Calendar.DATE, -1);
        Date day2 = cal.getTime();
        String str = "";
        Date now = new Date();
        long n = now.getTime() - date.getTime();
        if (n < 60 * 1000 && n > 0) {//1分钟内
            str = n / 1000 + "秒前";
        } else if (n >= 60 * 1000 && n < 60 * 60 * 1000) {//1小时内
            str = n / (60 * 1000) + "分钟前";
        } else {
            if (date.getTime() > day0.getTime()) {//当天
                str = "今天" + DateFormatUtils.format(date, "HH:mm");
            } else if (date.getTime() > day1.getTime()) {//昨天
                str = "昨天" + DateFormatUtils.format(date, "HH:mm");
            } else if (date.getTime() > day2.getTime()) {//前天
                str = "前天" + DateFormatUtils.format(date, "HH:mm");
            } else {
                str = DateFormatUtils.format(date, "yyyy-MM-dd");
            }
        }
        return str;
    }

    /**
     * 获取指定日期公历年份
     * @param date 日期
     * @return
     */
    public static int getSolarYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

}
