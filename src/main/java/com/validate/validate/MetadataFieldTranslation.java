package com.validate.validate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wanchongyang
 * @date 2018/11/14 2:12 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetadataFieldTranslation {
    private String variableName;
    private String language;
    private String showName;
}
