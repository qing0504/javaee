package com.java8.time;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author wanchongyang
 * @date 2019-06-28 12:06
 */
public class DurationTest {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("00");

    public static void main(String[] args) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime end = LocalDateTime.now();
        System.out.println("end :" + end.format(df));
        LocalDateTime start = LocalDateTime.parse("2019-06-30 10:20:30", df);
        System.out.println("start :" + start.format(df));

        Duration duration = Duration.between(start, end);

        System.out.println("天：" + duration.toDays());
        System.out.println("时：" + (duration.toHours() - duration.toDays()*24));
        System.out.println("分：" + (duration.toMinutes() - duration.toHours()*60));
        System.out.println("秒：" + (duration.toSeconds() - duration.toMinutes()*60));

        System.out.println("=====================");
        System.out.println("天：" + DECIMAL_FORMAT.format(duration.toDaysPart()));
        System.out.println("时：" + DECIMAL_FORMAT.format(duration.toHoursPart()));
        System.out.println("分：" + DECIMAL_FORMAT.format(duration.toMinutesPart()));
        System.out.println("秒：" + DECIMAL_FORMAT.format(duration.toSecondsPart()));
    }
}
