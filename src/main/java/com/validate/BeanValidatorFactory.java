package com.validate;

import com.validate.constant.MessageConstant;
import com.validate.constant.ValidatorConstant;

/**
 * @author wanchongyang
 * @date 2018/11/8 2:10 PM
 */
public class BeanValidatorFactory {
    public static BeanValidator newInstance(String type) {
        if (ValidatorConstant.VALIDATOR_TYPE_EMAIL.equals(type)) {
            MutableValidatorPropertyValues propertyValues = new MutableValidatorPropertyValues();
            propertyValues.addPropertyValue(ValidatorConstant.ERROR_MSG_CN_ATTRIBUTE, MessageConstant.EnumEmailValidator.CN.getErrorMsg());
            propertyValues.addPropertyValue(ValidatorConstant.ERROR_MSG_EN_ATTRIBUTE, MessageConstant.EnumEmailValidator.EN.getErrorMsg());
            return new EmailBeanValidator(propertyValues);
        } else if (ValidatorConstant.VALIDATOR_TYPE_INTRANGE.equals(type)) {
            MutableValidatorPropertyValues propertyValues = new MutableValidatorPropertyValues();
            propertyValues.addPropertyValue(ValidatorConstant.ERROR_MSG_CN_ATTRIBUTE, MessageConstant.EnumIntRangeValidator.CN.getErrorMsg());
            propertyValues.addPropertyValue(ValidatorConstant.ERROR_MSG_EN_ATTRIBUTE, MessageConstant.EnumIntRangeValidator.EN.getErrorMsg());
            propertyValues.addPropertyValue(new ValidatorPropertyValue(IntRangeBeanValidator.MIN_ATTRIBUTE, String.valueOf(Integer.MIN_VALUE)));
            propertyValues.addPropertyValue(new ValidatorPropertyValue(IntRangeBeanValidator.MAX_ATTRIBUTE, String.valueOf(Integer.MAX_VALUE)));
            return new IntRangeBeanValidator(propertyValues);
        } else if (ValidatorConstant.VALIDATOR_TYPE_REGEX.equals(type)) {
            MutableValidatorPropertyValues propertyValues = new MutableValidatorPropertyValues();
            propertyValues.addPropertyValue(ValidatorConstant.ERROR_MSG_CN_ATTRIBUTE, MessageConstant.EnumRegexValidator.CN.getErrorMsg());
            propertyValues.addPropertyValue(ValidatorConstant.ERROR_MSG_EN_ATTRIBUTE, MessageConstant.EnumRegexValidator.EN.getErrorMsg());
            return new RegexBeanValidator(propertyValues);
        }

        throw new IllegalArgumentException("illegal type " + type);
    }
}
