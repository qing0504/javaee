package com.validate.constant;

import com.validate.contract.EmailValidator;
import com.validate.contract.IntRangeValidator;
import com.validate.contract.RegexValidator;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认message常量类
 * @author wanchongyang
 * @date 2018/11/6 2:22 PM
 */
public class MessageConstant {
    private static final ConcurrentHashMap<Class<?>, String> map = new ConcurrentHashMap<>(64);

    static {
        register(EmailValidator.class, "邮箱不合法");
        register(IntRangeValidator.class, "整数应介于{0}与{1}之间");
        register(RegexValidator.class, "正则验证不匹配");
    }

    private MessageConstant() {

    }

    public static void register(Class clazz, String message) {
        map.put(clazz, message);
    }

    public static String get(Class clazz) {
        return map.get(clazz);
    }
}
