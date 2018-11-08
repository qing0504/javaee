package com.validate;

import com.common.utils.RegexUtil;
import com.validate.constant.MessageConstant;
import com.validate.constant.ValidatorConstant;
import com.validate.exception.BeanValidatorMetadataException;

/**
 * @author wanchongyang
 * @date 2018/11/8 11:13 AM
 */
public class IntRangeBeanValidator extends AbstractBeanValidator {
    public static final String MIN_ATTRIBUTE = "min";
    public static final String MAX_ATTRIBUTE = "max";

    public IntRangeBeanValidator() {
        MutableValidatorPropertyValues propertyValues = new MutableValidatorPropertyValues();
        propertyValues.addPropertyValue(ValidatorConstant.ERROR_MSG_CN_ATTRIBUTE, MessageConstant.EnumIntRangeValidator.CN.getErrorMsg());
        propertyValues.addPropertyValue(ValidatorConstant.ERROR_MSG_EN_ATTRIBUTE, MessageConstant.EnumIntRangeValidator.EN.getErrorMsg());
        propertyValues.addPropertyValue(new ValidatorPropertyValue(IntRangeBeanValidator.MIN_ATTRIBUTE, String.valueOf(Integer.MIN_VALUE)));
        propertyValues.addPropertyValue(new ValidatorPropertyValue(IntRangeBeanValidator.MAX_ATTRIBUTE, String.valueOf(Integer.MAX_VALUE)));
        super.setPropertyValues(propertyValues);
    }

    @Override
    public String getType() {
        return ValidatorConstant.VALIDATOR_TYPE_INTRANGE;
    }

    @Override
    public void doVerifyMetadata() {
        ValidatorPropertyValues propertyValues = this.getPropertyValues();
        if (!propertyValues.contains(MIN_ATTRIBUTE) && !propertyValues.contains(MAX_ATTRIBUTE)) {
            throw new BeanValidatorMetadataException("illegal IntRange validator. at least one attribute is must.");
        }

        int minInt = Integer.MIN_VALUE;
        if (propertyValues.contains(MIN_ATTRIBUTE)) {
            String min = propertyValues.getPropertyValue(MIN_ATTRIBUTE).getValue();
            if (!RegexUtil.checkDigit(min)) {
                throw new BeanValidatorMetadataException("illegal IntRange validator. 'min' attribute value is illegal." + min);
            }

            minInt = Integer.parseInt(min);
        }

        int maxInt = Integer.MAX_VALUE;
        if (propertyValues.contains(MAX_ATTRIBUTE)) {
            String max = propertyValues.getPropertyValue(MAX_ATTRIBUTE).getValue();
            if (!RegexUtil.checkDigit(max)) {
                throw new BeanValidatorMetadataException("illegal IntRange validator. 'max' attribute value is illegal." + max);
            }

            maxInt = Integer.parseInt(max);
        }

        if (minInt > maxInt) {
            throw new BeanValidatorMetadataException("illegal IntRange validator. 'max' attribute value is greater than 'min' attribute value.");
        }
    }
}
