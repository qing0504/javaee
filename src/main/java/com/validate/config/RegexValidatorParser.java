package com.validate.config;

import com.validate.constant.ValidatorConstant;
import com.validate.contract.RegexValidator;
import com.validate.contract.Validator;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;

import java.util.Objects;

/**
 * @author wanchongyang
 * @date 2018/11/6 3:16 PM
 */
public class RegexValidatorParser extends AbstractValidatorParser implements ValidatorParser{
    public RegexValidatorParser() {
        register(ValidatorConstant.VALIDATOR_TYPE_REGEX, this);
    }

    @Override
    public Validator parse(Element element) {
        Objects.requireNonNull(element);

        String category = getAttributeValue("category", element);
        String type = getAttributeValue("type", element);
        String msg = getAttributeValue("msg", element);
        if (StringUtils.isBlank(type)) {
            throw new IllegalArgumentException("Element attribute[type] is not blank.");
        }

        Element property = element.element("property");
        String regex = getAttributeValue("regex", property);
        if (StringUtils.isBlank(type)) {
            throw new IllegalArgumentException("Element attribute[regex] is not blank.");
        }

        RegexValidator regexValidator = new RegexValidator(category, type, msg, regex);

        return regexValidator;
    }
}
