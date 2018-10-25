package com.java8.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author wanchongyang
 * @date 2018/6/26 上午11:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SubAccount extends Account {
    private String subName;
}
