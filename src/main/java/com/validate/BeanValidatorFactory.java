package com.validate;

import com.validate.constant.ValidatorConstant;
import com.validate.exception.BeanValidatorInstantiationException;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wanchongyang
 * @date 2018/11/8 2:10 PM
 */
public class BeanValidatorFactory {
    private static final ConcurrentHashMap<String, Class<? extends BeanValidator>> clazzCache = new ConcurrentHashMap<>(64);

    static {
        register(ValidatorConstant.VALIDATOR_TYPE_REGEX, RegexBeanValidator.class);
        register(ValidatorConstant.VALIDATOR_TYPE_INTRANGE, IntRangeBeanValidator.class);
        register(ValidatorConstant.VALIDATOR_TYPE_EMAIL, EmailBeanValidator.class);
    }
    private BeanValidatorFactory() {

    }

    public static BeanValidator newInstance(String type) {
        if (!clazzCache.containsKey(type)) {
            throw new IllegalArgumentException("illegal type " + type);
        }

        return newInstance(clazzCache.get(type));
    }

    public static BeanValidator newInstance(Class<? extends BeanValidator> clazz) {
        try {
           return clazz.newInstance();
        } catch (Exception e) {
            throw new BeanValidatorInstantiationException("BeanValidator InstantiationException", e);
        }
    }

    private static void register(String type, Class<? extends BeanValidator> clazz) {
        clazzCache.putIfAbsent(type, clazz);
    }
}
