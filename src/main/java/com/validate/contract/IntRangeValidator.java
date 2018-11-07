package com.validate.contract;

import com.validate.constant.MessageConstant;
import lombok.Data;

/**
 * 正整数范围验证器对象
 * @author wanchongyang
 * @date 2018/11/6 1:56 PM
 */
@Data
public class IntRangeValidator extends RootValidator {
    private Integer min;
    private Integer max;

    public IntRangeValidator(String category, String type, String msg, Integer min, Integer max) {
        this(category, type, msg);
        this.min = min;
        this.max = max;
    }

    public IntRangeValidator(String category, String type, String msg) {
        super(category, type, msg);
    }

    @Override
    String getDefaultMsg() {
        return MessageConstant.get(this.getClass());
    }
}
