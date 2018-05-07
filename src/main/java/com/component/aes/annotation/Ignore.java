package com.component.aes.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 忽略加解密属性注解，配合注解@Encrypt和@Decrypt一起使用
 * @author wanchongyang
 * @date 2018/5/6 下午2:06
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Ignore {
}
