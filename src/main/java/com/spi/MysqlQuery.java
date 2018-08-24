package com.spi;

/**
 * @author wanchongyang
 * @date 2018/8/22 下午3:52
 */
public class MysqlQuery implements DatabaseInterface {
    @Override
    public void query() {
        System.out.println("mysql database query something.");
    }
}
