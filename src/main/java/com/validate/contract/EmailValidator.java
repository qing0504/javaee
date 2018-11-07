package com.validate.contract;

import com.validate.constant.MessageConstant;
import lombok.Data;

/**
 * 邮箱验证器对象
 * @author wanchongyang
 * @date 2018/11/6 1:59 PM
 */
@Data
public class EmailValidator extends RootValidator {
    public EmailValidator(String category, String type, String msg) {
        super(category, type, msg);
    }

    @Override
    String getDefaultMsg() {
        return MessageConstant.get(this.getClass());
    }
}
