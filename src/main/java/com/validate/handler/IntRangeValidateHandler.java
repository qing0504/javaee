package com.validate.handler;

import com.common.utils.RegexUtil;
import com.validate.BeanValidator;
import com.validate.IntRangeBeanValidator;
import com.validate.constant.ValidatorConstant;
import com.validate.validate.MetadataTitleField;
import com.validate.validate.ValidateItem;
import com.validate.validate.ValidateResult;

import java.text.MessageFormat;

/**
 * @author wanchongyang
 * @date 2018/11/14 4:03 PM
 */
public class IntRangeValidateHandler implements ValidateHandler {

    @Override
    public void validate(BeanValidator validator, MetadataTitleField titleField, ValidateItem item) {
        // 最小值
        String min = validator.getPropertyValues().getPropertyValue(IntRangeBeanValidator.MIN_ATTRIBUTE).getValue();
        int minInt = Integer.parseInt(min);

        // 最大值
        String max = validator.getPropertyValues().getPropertyValue(IntRangeBeanValidator.MAX_ATTRIBUTE).getValue();
        int maxInt = Integer.parseInt(max);

        String temp = item.getRowData().get(titleField.getVariableName());
        if (!RegexUtil.checkDigit(temp)) {
            String errorMsg = validator.getPropertyValues().getPropertyValue(ValidatorConstant.ERROR_MSG_CN_ATTRIBUTE).getValue();
            if (ValidatorConstant.EN.equals(titleField.getLanguage())) {
                errorMsg = validator.getPropertyValues().getPropertyValue(ValidatorConstant.ERROR_MSG_EN_ATTRIBUTE).getValue();
            }
            item.setValidateResult(ValidateResult.ERROR(MessageFormat.format(errorMsg, min, max)));
            return;
        }

        int tempInt = Integer.parseInt(temp);
        if (tempInt < minInt || tempInt > maxInt) {
            String errorMsg = validator.getPropertyValues().getPropertyValue(ValidatorConstant.ERROR_MSG_CN_ATTRIBUTE).getValue();
            if (ValidatorConstant.EN.equals(titleField.getLanguage())) {
                errorMsg = validator.getPropertyValues().getPropertyValue(ValidatorConstant.ERROR_MSG_EN_ATTRIBUTE).getValue();
            }
            item.setValidateResult(ValidateResult.ERROR(MessageFormat.format(errorMsg, min, max)));
        }

    }
}
