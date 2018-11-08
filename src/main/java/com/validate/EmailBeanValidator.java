package com.validate;

import com.validate.constant.ValidatorConstant;

/**
 * @author wanchongyang
 * @date 2018/11/8 10:45 AM
 */
public class EmailBeanValidator extends AbstractBeanValidator {
    public EmailBeanValidator(MutableValidatorPropertyValues propertyValues) {
        super.setPropertyValues(propertyValues);
    }

    @Override
    public String getType() {
        return ValidatorConstant.VALIDATOR_TYPE_EMAIL;
    }

    @Override
    public void doVerifyMetadata() {

    }
}
