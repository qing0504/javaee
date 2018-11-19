package com.validate;

import com.validate.handler.ValidateHandler;

/**
 * @author wanchongyang
 * @date 2018/11/16 4:07 PM
 */
public class CustomBeanValidator extends AbstractBeanValidator {
    @Override
    public String getType() {
        return null;
    }

    @Override
    public void doVerifyMetadata() {

    }

    @Override
    public ValidateHandler getHandler() {
        return ValidateHandler.SUCCESS;
    }
}
