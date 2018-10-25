package com.java8.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * @author wanchongyang
 * @date 2018/6/26 上午9:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuperAccount implements Serializable {
    private String superName;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
