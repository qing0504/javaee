package com.validate.exception;

/**
 * @author wanchongyang
 * @date 2018/11/8 4:29 PM
 */
public class BeanValidatorInstantiationException extends RuntimeException {
    /**
     * 错误消息
     */
    private String errorMsg;

    public BeanValidatorInstantiationException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public BeanValidatorInstantiationException(String errorMsg, Throwable t) {
        super(errorMsg, t);
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
