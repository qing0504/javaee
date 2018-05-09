package com.component.aes.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * @author wanchongyang
 * @date 2018/5/9 下午1:44
 */
@Getter @Setter
public abstract class BaseEntity {
    private Integer id;
    private Date timeCreated;
    private Date timeModified;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
