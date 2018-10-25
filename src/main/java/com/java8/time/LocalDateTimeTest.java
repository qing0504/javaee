package com.java8.time;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Date;

/**
 * @see java.time.LocalDateTime
 *
 * @author wanchongyang
 * @date 2018/10/24 7:30 PM
 */
public class LocalDateTimeTest {
    public static void main(String[] args) {
        LocalDateTime sylvester = LocalDateTime.of(2018, Month.OCTOBER, 26, 14, 8, 8);

        DayOfWeek dayOfWeek = sylvester.getDayOfWeek();
        System.out.println(dayOfWeek);

        Month month = sylvester.getMonth();
        System.out.println(month);

        // 获取指定字段的long值，相对0
        long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
        System.out.println(minuteOfDay);

        Instant instant = sylvester
                .atZone(ZoneId.systemDefault())
                .toInstant();

        Date legacyDate = Date.from(instant);
        System.out.println(legacyDate);

        DateTimeFormatter formatter =
                DateTimeFormatter
                        .ofPattern("MMM dd, yyyy - HH:mm");

        LocalDateTime parsed = LocalDateTime.parse("十一月 03, 2018 - 07:13", formatter);
        String string = parsed.format(formatter);
        System.out.println(string);

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.format(formatter));

        String formatStr = "2018年10月25日 08时08分08秒";
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒");
        LocalDateTime fullDateTime = LocalDateTime.parse(formatStr, dtFormatter);
        System.out.println(fullDateTime);

        // Java8的DateTimeFormatter是线程安全的，而SimpleDateFormat并不是线程安全。
        System.out.println(format(localDateTimeToDate(fullDateTime)));
        System.out.println(dateToLocalDateTime(new Date(System.currentTimeMillis())).format(dtFormatter));
    }


    /**
     * 要在高并发环境下能有比较好的体验，可以使用ThreadLocal来限制SimpleDateFormat只能在线程内共享，这样就避免了多线程导致的线程安全问题。
     */
    private static ThreadLocal<DateFormat> threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    // private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>() {
    //     @Override
    //     protected DateFormat initialValue() {
    //         return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //     }
    // };

    public static String format(Date date) {
        return threadLocal.get().format(date);
    }

    /**
     * Date转换为LocalDateTime
     * @param date
     * @return
     */
    private static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        return zonedDateTime.toLocalDateTime();
    }

    /**
     * LocalDateTime转换为Date
     * @param localDateTime
     * @return
     */
    private static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        Instant instant = zonedDateTime.toInstant();
        return Date.from(instant);
    }
}
