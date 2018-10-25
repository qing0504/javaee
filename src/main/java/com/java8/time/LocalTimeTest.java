package com.java8.time;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

/**
 * @see java.time.LocalTime
 *
 * @author wanchongyang
 * @date 2018/10/24 7:29 PM
 */
public class LocalTimeTest {
    public static void main(String[] args) {
        // get the current time
        Clock clock = Clock.systemDefaultZone();
        long t0 = clock.millis();
        System.out.println(t0);
        System.out.println(System.currentTimeMillis());

        // 东八区
        ZoneId zone1 = ZoneId.of("Asia/Shanghai");
        // 西六区
        ZoneId zone2 = ZoneId.of("America/Chicago");

        System.out.println(zone1.getRules());
        System.out.println(zone2.getRules());

        // time
        LocalTime now1 = LocalTime.now(zone1);
        LocalTime now2 = LocalTime.now(zone2);

        System.out.println(now1);
        System.out.println(now2);

        System.out.println(now1.isBefore(now2));

        long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
        long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);
        System.out.println(hoursBetween);
        System.out.println(minutesBetween);

        // create time
        LocalTime now = LocalTime.now();
        System.out.println(now);

        LocalTime late = LocalTime.of(23, 59, 59);
        System.out.println(late);

        DateTimeFormatter formatter =
                DateTimeFormatter
                        .ofLocalizedTime(FormatStyle.SHORT)
                        .withLocale(Locale.CHINA);
        System.out.println(formatter.format(now));

        LocalTime leetTime = LocalTime.parse("下午3:37", formatter);
        System.out.println(leetTime);

        // to legacy date
        Instant instant = clock.instant();
        Date legacyDate = Date.from(instant);
        System.out.println(legacyDate);
    }
}
