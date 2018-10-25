package com.java8.defaultmethods;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * @author wanchongyang
 * @date 2018/4/18 下午4:37
 */
public class SimpleZoneTimeClient implements AbstractZoneTimeClient {
    @Override
    public void setTime(int hour, int minute, int second) {

    }

    @Override
    public void setDate(int day, int month, int year) {

    }

    @Override
    public void setDateAndTime(int day, int month, int year, int hour, int minute, int second) {

    }

    @Override
    public LocalDateTime getLocalDateTime() {
        return null;
    }

    @Override
    public ZonedDateTime getZonedDateTime(String zoneString) {
        return null;
    }
}
