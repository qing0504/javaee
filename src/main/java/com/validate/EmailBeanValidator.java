package com.validate;

import com.validate.constant.MessageConstant;
import com.validate.constant.ValidatorConstant;
import com.validate.handler.EmailValidateHandler;
import com.validate.handler.ValidateHandler;

/**
 * @author wanchongyang
 * @date 2018/11/8 10:45 AM
 */
public class EmailBeanValidator extends AbstractBeanValidator {
    public EmailBeanValidator() {
        MutableValidatorPropertyValues propertyValues = new MutableValidatorPropertyValues();
        propertyValues.addPropertyValue(ValidatorConstant.ERROR_MSG_CN_ATTRIBUTE, MessageConstant.EnumEmailValidator.CN.getErrorMsg());
        propertyValues.addPropertyValue(ValidatorConstant.ERROR_MSG_EN_ATTRIBUTE, MessageConstant.EnumEmailValidator.EN.getErrorMsg());
        super.setPropertyValues(propertyValues);
    }

    @Override
    public String getType() {
        return ValidatorConstant.VALIDATOR_TYPE_EMAIL;
    }

    @Override
    public void doVerifyMetadata() {

    }

    @Override
    public ValidateHandler getHandler() {
        return new EmailValidateHandler();
    }
}
