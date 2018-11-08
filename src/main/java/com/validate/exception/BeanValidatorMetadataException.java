package com.validate.exception;

/**
 * @author wanchongyang
 * @date 2018/11/8 4:29 PM
 */
public class BeanValidatorMetadataException extends RuntimeException {
    /**
     * 错误消息
     */
    private String errorMsg;

    public BeanValidatorMetadataException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public BeanValidatorMetadataException(String errorMsg, Throwable t) {
        super(errorMsg, t);
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
