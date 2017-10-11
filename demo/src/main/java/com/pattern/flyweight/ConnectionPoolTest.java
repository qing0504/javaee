package com.pattern.flyweight;

/**
 * Created by wanchongyang on 2017/10/11.
 */
public class ConnectionPoolTest {
    public static void main(String[] args) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.getConnection();
        connectionPool.release();
    }
}
