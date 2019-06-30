package com.spi.log.impl;

import com.spi.log.Log;

/**
 * @author wanchongyang
 * @date 2019-06-30 21:14
 */
public class ChineseLog implements Log {
    @Override
    public void say() {
        System.out.println("中文日志。");
    }
}
