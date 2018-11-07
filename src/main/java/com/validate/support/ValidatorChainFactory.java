package com.validate.support;

import org.dom4j.Element;

/**
 * ValidatorChain 工厂接口
 *
 * @author wanchongyang
 * @date 2018/11/6 5:03 PM
 */
public interface ValidatorChainFactory {
    ValidatorChain getChain(String paraName, String variableName);

    ValidatorChain getChain(String paraName, String variableName, Element element);
}
