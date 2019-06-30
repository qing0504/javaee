package com.spi.log;

import com.spi.annotation.SPI;

/**
 * @author wanchongyang
 * @date 2019-06-30 21:12
 */
@SPI("chinese")
public interface Log {
    void say();
}
