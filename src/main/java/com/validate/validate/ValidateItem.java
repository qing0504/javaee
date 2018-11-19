package com.validate.validate;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashMap;

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
    private LinkedHashMap<String, String> rowData;

    private ValidateItem() {
        this.validateResult = ValidateResult.SUCCESS;
        this.rowData = new LinkedHashMap<>(64);
    }

    public static ValidateItem build() {
        return new ValidateItem();
    }

    public ValidateItem add(String key, String value) {
        this.rowData.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        return rowData.isEmpty() ? "" : StringUtils.join(this.rowData.values().toArray(), "|$|");
    }
}
