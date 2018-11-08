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

    /**
     * CN 邮箱验证中文错误信息
     * EN 邮箱验证英文错误信息
     */
    public enum EnumEmailValidator {
        CN("邮箱不合法"),
        EN("illegal email");

        private String errorMsg;

        EnumEmailValidator(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        public String getErrorMsg() {
            return errorMsg;
        }

        @Override
        public String toString() {
            return this.errorMsg;
        }
    }

    /**
     * CN 正则验证中文错误信息
     * EN 正则验证英文错误信息
     */
    public enum EnumRegexValidator {
        CN("正则验证不匹配"),
        EN("regex validate is not matched");

        private String errorMsg;

        EnumRegexValidator(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        public String getErrorMsg() {
            return errorMsg;
        }

        @Override
        public String toString() {
            return this.errorMsg;
        }
    }
    /**
     * CN 整数区间验证中文错误信息
     * EN 整数区间验证英文错误信息
     */
    public enum EnumIntRangeValidator {
        CN("整数应介于{0}与{1}之间"),
        EN("the integer should be between {0} and {1}");

        private String errorMsg;

        EnumIntRangeValidator(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        public String getErrorMsg() {
            return errorMsg;
        }

        @Override
        public String toString() {
            return this.errorMsg;
        }
    }
}
