package com.defaultmethods;

import java.time.ZonedDateTime;

/**
 * Any class that implements the interface AbstractZoneTimeClient will have to implement the method getZonedDateTime;
 * this method is an abstract method like all other nondefault (and nonstatic) methods in an interface.
 * @author wanchongyang
 * @date 2018/4/18 下午4:35
 */
public interface AbstractZoneTimeClient extends TimeClient{
    @Override
    ZonedDateTime getZonedDateTime(String zoneString);
}
