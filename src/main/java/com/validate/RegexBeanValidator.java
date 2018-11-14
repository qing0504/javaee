package com.validate;

import com.validate.constant.MessageConstant;
import com.validate.constant.ValidatorConstant;
import com.validate.exception.BeanValidatorMetadataException;
import com.validate.handler.RegexValidateHandler;
import com.validate.handler.ValidateHandler;

import java.util.regex.Pattern;

/**
 * @author wanchongyang
 * @date 2018/11/8 11:14 AM
 */
public class RegexBeanValidator extends AbstractBeanValidator {
    public static final String REGEX_ATTRIBUTE = "regex";

    private Pattern pattern;

    public RegexBeanValidator() {
        MutableValidatorPropertyValues propertyValues = new MutableValidatorPropertyValues();
        propertyValues.addPropertyValue(ValidatorConstant.ERROR_MSG_CN_ATTRIBUTE, MessageConstant.EnumRegexValidator.CN.getErrorMsg());
        propertyValues.addPropertyValue(ValidatorConstant.ERROR_MSG_EN_ATTRIBUTE, MessageConstant.EnumRegexValidator.EN.getErrorMsg());
        super.setPropertyValues(propertyValues);
    }

    @Override
    public String getType() {
        return ValidatorConstant.VALIDATOR_TYPE_REGEX;
    }

    @Override
    public void doVerifyMetadata() {
        if (!this.getPropertyValues().contains(REGEX_ATTRIBUTE)) {
            throw new BeanValidatorMetadataException("illegal Regex validator.");
        }

        this.pattern = Pattern.compile(this.getPropertyValues().getPropertyValue(REGEX_ATTRIBUTE).getValue());
    }

    @Override
    public ValidateHandler getHandler() {
        return new RegexValidateHandler();
    }

    public Pattern getPattern() {
        return pattern;
    }
}
