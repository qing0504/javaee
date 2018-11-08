package com.validate.config;

import com.common.utils.RegexUtil;
import com.validate.constant.ValidatorConstant;
import com.validate.contract.IntRangeValidator;
import com.validate.contract.Validator;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;

/**
 * @author wanchongyang
 * @date 2018/11/6 4:22 PM
 */
public class IntRangeValidatorParser extends AbstractValidatorParser implements ValidatorParser{
    public IntRangeValidatorParser() {
        register(ValidatorConstant.VALIDATOR_TYPE_INTRANGE, this);
    }

    @Override
    public Validator parse(Element element) {
        String category = getAttributeValue("category", element);
        String type = getAttributeValue("type", element);
        String msg = getAttributeValue("msg", element);
        if (StringUtils.isBlank(type)) {
            throw new IllegalArgumentException("Element attribute[type] is not blank.");
        }

        Element property = element.element("property");
        String min = getAttributeValue("min", property);
        String max = getAttributeValue("max", property);
        if (StringUtils.isBlank(min) && StringUtils.isBlank(max)) {
            throw new IllegalArgumentException("Element attribute[min] and attribute[max] is at least one.");
        }

        IntRangeValidator intRangeValidator = new IntRangeValidator(category, type, msg);
        if (StringUtils.isBlank(min)) {
            intRangeValidator.setMin(Integer.MIN_VALUE);
        } else {
            if (!RegexUtil.checkDigit(min)) {
                throw new IllegalArgumentException("Element attribute[min] value is illegal.");
            }

            intRangeValidator.setMin(Integer.valueOf(min));
        }

        if (StringUtils.isBlank(max)) {
            intRangeValidator.setMax(Integer.MAX_VALUE);
        } else {
            if (!RegexUtil.checkDigit(max)) {
                throw new IllegalArgumentException("Element attribute[max] value is illegal.");
            }

            intRangeValidator.setMax(Integer.valueOf(max));
        }

        return intRangeValidator;
    }
}
