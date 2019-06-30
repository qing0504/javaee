package com.spi.log.impl;

import com.spi.log.Log;

/**
 * @author wanchongyang
 * @date 2019-06-30 21:13
 */
public class EnglishLog implements Log {
    @Override
    public void say() {
        System.out.println("English Log.");
    }
}
