package com.validate.validate;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wanchongyang
 * @date 2018/11/9 11:22 AM
 */
@Data
public class ValidateItem {
    /**
     * 验证结果
     */
    private ValidateResult validateResult;
    /**
     * 行数据，key：标题属性名，value：标题列对应的值
     */
    private Map<String, String> rowData;

    private ValidateItem() {
        this.validateResult = ValidateResult.SUCCESS;
        this.rowData = new HashMap<>(64);
    }

    public static ValidateItem build() {
        return new ValidateItem();
    }

    public ValidateItem add(String key, String value) {
        this.rowData.put(key, value);
        return this;
    }
}
