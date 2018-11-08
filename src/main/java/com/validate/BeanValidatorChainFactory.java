package com.validate;

import org.w3c.dom.Element;

/**
 * ValidatorChain 工厂接口
 *
 * @author wanchongyang
 * @date 2018/11/6 5:03 PM
 */
public interface BeanValidatorChainFactory {
    BeanValidatorChain getChain(String paraName, String variableName);

    BeanValidatorChain getChain(String paraName, String variableName, Element element);
}
