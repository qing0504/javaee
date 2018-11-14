package com.validate.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanchongyang
 * @date 2018/11/7 1:57 PM
 */
public class ValidatorConstant {
    public static final List<String> typeCache = new ArrayList<>();

    /**
     * 验证器类别：邮箱
     */
    public static final String VALIDATOR_TYPE_EMAIL = "Email";

    /**
     * 验证器类别：正则
     */
    public static final String VALIDATOR_TYPE_REGEX = "Regex";
    /**
     * 验证器类别：整数区间
     */
    public static final String VALIDATOR_TYPE_INTRANGE = "IntRange";
    /**
     * 错误信息中文属性名
     */
    public static final String ERROR_MSG_CN_ATTRIBUTE = "errorMsgCn";
    public static final String CN = "cn";
    /**
     * 错误信息英文属性名
     */
    public static final String ERROR_MSG_EN_ATTRIBUTE = "errorMsgEn";
    public static final String EN = "en";

    static {
        typeCache.add(VALIDATOR_TYPE_EMAIL);
        typeCache.add(VALIDATOR_TYPE_REGEX);
        typeCache.add(VALIDATOR_TYPE_INTRANGE);
    }

    public static boolean contains(String type) {
        return typeCache.contains(type);
    }
}
