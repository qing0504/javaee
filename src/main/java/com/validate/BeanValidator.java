package com.validate;

import com.validate.handler.ValidateHandler;

/**
 * @author wanchongyang
 * @date 2018/11/8 9:55 AM
 */
public interface BeanValidator {
    /**
     * 获取验证器类型，唯一
     * @return
     */
    String getType();

    /**
     * 验证实例化的验证器（属性值）是否可用
     */
    void doVerifyMetadata();

    /**
     * 获取Validator的Property集合
     * @return
     */
    MutableValidatorPropertyValues getPropertyValues();

    /**
     * 获取验证处理器，Validator唯一绑定一个ValidateHandler
     * @return
     */
    ValidateHandler getHandler();
}
