package com.ioc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wanchongyang
 * @date 2018/7/21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Component {
    /**
     * Bean Name
     */
    String value() default "";

    /**
     * 作用域
     */
    String scope() default "singleton";
}
