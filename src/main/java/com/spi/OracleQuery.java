package com.spi;

/**
 * @author wanchongyang
 * @date 2018/8/22 下午3:54
 */
public class OracleQuery implements DatabaseInterface {
    @Override
    public void query() {
        System.out.println("Oracle database query something.");
    }
}
