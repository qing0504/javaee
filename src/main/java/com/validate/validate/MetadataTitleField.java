package com.validate.validate;

import com.validate.BeanValidatorChain;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * xml表头配置
 * @author wanchongyang
 * @date 2018/11/9 11:30 AM
 */
@Data
public class MetadataTitleField {
    private String variableName;
    private String validateRuleText;
    private String language;
    private Map<String, MetadataFieldTranslation> fieldTranslationMap;
    private BeanValidatorChain chain;

    public MetadataTitleField addFieldTranslation(MetadataFieldTranslation fieldTranslation) {
        this.fieldTranslationMap.put(fieldTranslation.getLanguage(), fieldTranslation);
        return this;
    }

    public MetadataTitleField() {
        this.fieldTranslationMap = new HashMap<>(64);
        this.chain = new BeanValidatorChain();
        this.language = "cn";
    }

    public MetadataTitleField(String variableName) {
        this();
        this.variableName = variableName;
    }

    public MetadataTitleField setChain(BeanValidatorChain chain) {
        this.chain = chain;
        return this;
    }
}
