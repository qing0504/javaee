package com.pattern.flyweight;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Created by wanchongyang on 2017/10/11.
 */
public class ConnectionPool {
    private Vector<Connection> pool;

    /*公有属性*/
    private String url = "jdbc:mysql://localhost:3306/test";
    private String username = "root";
    private String password = "tiger";
    private String driverClassName = "com.mysql.jdbc.Driver";

    private int poolSize = 10;
    //private static ConnectionPool instance = null;
    Connection conn = null;

    /*构造方法，做一些初始化工作*/
    private ConnectionPool() {
        pool = new Vector<Connection>(poolSize);

        for (int i = 0; i < poolSize; i++) {
            try {
                Class.forName(driverClassName);
                conn = DriverManager.getConnection(url, username, password);
                pool.add(conn);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static ConnectionPool getInstance() {
        return ConnectionPoolHolder.instance;
    }

    private static class ConnectionPoolHolder {
        private static final ConnectionPool instance = new ConnectionPool();
    }

    /* 返回连接到连接池 */
    public synchronized void release() {
        pool.add(conn);
        System.out.println("release connection.");
    }

    /* 返回连接池中的一个数据库连接 */
    public synchronized Connection getConnection() {
        System.out.println("get connection");
        if (pool.size() > 0) {
            Connection conn = pool.get(0);
            pool.remove(conn);
            return conn;
        } else {
            return null;
        }
    }
}
