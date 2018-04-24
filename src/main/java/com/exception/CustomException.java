package com.exception;

/**
 * 自定义异常类(继承运行时异常)
 * @author wanchongyang
 * @date 2018/4/24 下午4:31
 */
public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 错误编码
     */
    private int errorCode;

    /**
     * 错误消息
     */
    private String errorMsg;

    public CustomException(String message) {
        super(message);
    }

    public CustomException(int errorCode, String errorMsg) {
        this(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    /**
     * 构造一个基本异常
     * @param errorCode 错误编码
     * @param errorMsg 信息描述
     * @param cause 根异常类（可以存入任何异常）
     */
    public CustomException(int errorCode, String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
        this.errorMsg = message;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }
}
