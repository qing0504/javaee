package com.validate;

import com.validate.constant.ValidatorConstant;
import com.validate.exception.BeanValidatorMetadataException;

import java.util.regex.Pattern;

/**
 * @author wanchongyang
 * @date 2018/11/8 11:14 AM
 */
public class RegexBeanValidator extends AbstractBeanValidator {
    public static final String REGEX_ATTRIBUTE = "regex";

    private Pattern pattern;

    public RegexBeanValidator(MutableValidatorPropertyValues propertyValues) {
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

    public Pattern getPattern() {
        return pattern;
    }
}
