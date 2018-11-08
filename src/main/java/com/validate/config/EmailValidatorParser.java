package com.validate.config;

import com.validate.constant.ValidatorConstant;
import com.validate.contract.EmailValidator;
import com.validate.contract.Validator;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;

import java.util.Objects;

/**
 * @author wanchongyang
 * @date 2018/11/6 4:19 PM
 */
public class EmailValidatorParser extends AbstractValidatorParser implements ValidatorParser {
    public EmailValidatorParser() {
        register(ValidatorConstant.VALIDATOR_TYPE_EMAIL, this);
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

        EmailValidator emailValidator = new EmailValidator(category, type, msg);

        return emailValidator;
    }
}
