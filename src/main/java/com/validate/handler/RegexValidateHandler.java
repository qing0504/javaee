package com.validate.handler;

import com.validate.BeanValidator;
import com.validate.RegexBeanValidator;
import com.validate.constant.ValidatorConstant;
import com.validate.validate.MetadataTitleField;
import com.validate.validate.ValidateItem;
import com.validate.validate.ValidateResult;

/**
 * @author wanchongyang
 * @date 2018/11/14 4:17 PM
 */
public class RegexValidateHandler implements ValidateHandler {
    @Override
    public void validate(BeanValidator validator, MetadataTitleField titleField, ValidateItem item) {
        if (!(validator instanceof RegexBeanValidator)) {
            throw new IllegalArgumentException("it is not RegexBeanValidator`s instance." + validator);
        }

        RegexBeanValidator regexBeanValidator = (RegexBeanValidator) validator;
        String value = item.getRowData().get(titleField.getVariableName());
        if (!regexBeanValidator.getPattern().matcher(value).matches()) {
            String errorMsg = validator.getPropertyValues().getPropertyValue(ValidatorConstant.ERROR_MSG_CN_ATTRIBUTE).getValue();
            if (ValidatorConstant.EN.equals(titleField.getLanguage())) {
                errorMsg = validator.getPropertyValues().getPropertyValue(ValidatorConstant.ERROR_MSG_EN_ATTRIBUTE).getValue();
            }
            item.setValidateResult(ValidateResult.ERROR(errorMsg));
        }
    }
}
