package com.java8.time;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author wanchongyang
 * @date 2019-06-28 12:06
 */
public class DurationTest {
    public static void main(String[] args) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime end = LocalDateTime.now();
        System.out.println("end :" + end.format(df));
        LocalDateTime start = LocalDateTime.parse("2019-06-27 10:20:30", df);
        System.out.println("start :" + start.format(df));

        Duration duration = Duration.between(start, end);
        System.out.println("天：" + duration.toDays());
        System.out.println("时：" + (duration.toHours() - duration.toDays()*24));
        System.out.println("分：" + (duration.toMinutes() - duration.toHours()*60));
        System.out.println("秒：" + (duration.getSeconds() - duration.toMinutes()*60));
    }
}
