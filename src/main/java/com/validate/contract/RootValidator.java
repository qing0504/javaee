package com.validate.contract;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * 验证器根对象
 * @author wanchongyang
 * @date 2018/11/6 1:54 PM
 */
@Data
public abstract class RootValidator implements Validator{
    private String category;
    private String type;
    private String msg;

    public RootValidator(String category, String type, String msg) {
        this.category = category;
        this.type = type;
        this.msg = StringUtils.isBlank(msg) ? getDefaultMsg() : msg;
    }

    abstract String getDefaultMsg();
}
