package com.validate.validate;

import lombok.Data;

import java.util.*;

/**
 * @author wanchongyang
 * @date 2018/11/14 11:36 AM
 */
@Data
public class MetadataTitleFields {
    private List<MetadataTitleField> fieldList;
    private Map<String, MetadataTitleField> fieldMap;

    public MetadataTitleFields() {
        this.fieldList = new ArrayList<>();
        this.fieldMap = new HashMap<>(64);
    }

    public MetadataTitleFields addField(MetadataTitleField field) {
        Objects.requireNonNull(field);

        this.fieldList.add(field);
        this.fieldMap.put(field.getVariableName(), field);
        return this;
    }
}
