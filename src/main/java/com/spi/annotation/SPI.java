package com.spi.annotation;

import java.lang.annotation.*;

/**
 * @author wanchongyang
 * @date 2019-06-30 21:00
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SPI {
    /**
     * 缺省扩展点名
     */
    String value() default "";
}
