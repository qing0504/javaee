package com.component.aes.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 加密注解，可以作用于类、属性上
 * 使用说明：
 * 1、注解作用到类上，默认类下所有属性都加密，一般与@Ignore注解一起使用，属性上加@Ignore注解的会做不加密处理，只是单纯的值copy。
 *    这种适用类加密字段比较多场景
 * 2、注解作用到类的属性上，属性会做加密处理。这种适用类加密字段比较少场景
 * @author wanchongyang
 * @date 2018/5/6 下午1:57
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface Encrypt {
    /**
     *
     * @return
     */
    String value() default "";
}
