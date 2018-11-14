package com.validate.validate;

/**
 * 验证结果
 * @author wanchongyang
 * @date 2018/11/9 10:07 AM
 */
public class ValidateResult {
    /**
     * 验证结果标识：true 验证成功， false 验证失败
     */
    private boolean valid = false;
    /**
     * 验证失败错误信息
     */
    private String errorMsg;

    private ValidateResult(boolean valid) {
        this.valid = valid;
    }

    private ValidateResult(boolean valid, String errorMsg) {
        this(valid);
        this.errorMsg = errorMsg;
    }

    public static ValidateResult SUCCESS = new ValidateResult(true);

    public static ValidateResult ERROR() {
        return new ValidateResult(false);
    }
;
    public static ValidateResult ERROR(String errorMsg) {
        return new ValidateResult(false, errorMsg);
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
