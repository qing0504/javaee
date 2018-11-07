package com.validate.contract;

import com.validate.constant.MessageConstant;
import lombok.Data;

import java.util.regex.Pattern;

/**
 * 正则验证器对象
 * @author wanchongyang
 * @date 2018/11/6 2:00 PM
 */
@Data
public class RegexValidator extends RootValidator{
    private String regex;
    private Pattern pattern;

    public RegexValidator(String category, String type, String msg, String regex) {
        super(category, type, msg);
        this.regex = regex;
        this.pattern = Pattern.compile(regex);
    }

    @Override
    String getDefaultMsg() {
        return MessageConstant.get(this.getClass());
    }
}
