package com.validate.handler;

import com.validate.BeanValidator;
import com.validate.validate.MetadataTitleField;
import com.validate.validate.ValidateItem;
import com.validate.validate.ValidateResult;

/**
 * @author wanchongyang
 * @date 2018/11/14 3:37 PM
 */
public interface ValidateHandler {
    void validate(BeanValidator validator, MetadataTitleField titleField, ValidateItem item);

    ValidateHandler SUCCESS = new ValidateHandler() {
        @Override
        public void validate(BeanValidator validator, MetadataTitleField titleField, ValidateItem item) {
            item.setValidateResult(ValidateResult.SUCCESS);
        }
    };
}
