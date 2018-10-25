package com.java8.defaultmethods;

/**
 * @author wanchongyang
 * @date 2018/4/18 下午4:27
 */
public class TestSimpleTimeClient {
    public static void main(String... args) {
        TimeClient myTimeClient = new SimpleTimeClient();
        System.out.println("Current defaultmethods: " + myTimeClient.toString());
        System.out.println("Time in California: " +
                myTimeClient.getZonedDateTime("Blah blah").toString());
    }
}
