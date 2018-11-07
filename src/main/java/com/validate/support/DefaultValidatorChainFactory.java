package com.validate.support;

import com.validate.config.AbstractValidatorParser;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Default implementation of the {@link ValidatorChainFactory} interface.
 * @author wanchongyang
 * @date 2018/11/7 10:27 AM
 */
public class DefaultValidatorChainFactory extends AbstractValidatorParser implements ValidatorChainFactory{
    private static ConcurrentHashMap<String, ValidatorChain> validatorChainCache = new ConcurrentHashMap<>(64);

    private static final String CONNECTOR = "$";

    @Override
    public ValidatorChain getChain(String paraName, String variableName) {
        return validatorChainCache.get(getKey(paraName, variableName));
    }

    @Override
    public ValidatorChain getChain(String paraName, String variableName, Element element) {
        ValidatorChain chain = this.getChain(paraName, variableName);
        if (chain != null) {
            return chain;
        }

        ValidatorChain newChain = parse(element);
        chain = validatorChainCache.putIfAbsent(getKey(paraName, variableName), newChain);
        if (chain == null) {
            chain = newChain;
        }

        return chain;
    }

    private String getKey(String paraName, String variableName) {
        Objects.requireNonNull(paraName);
        Objects.requireNonNull(variableName);

        return paraName + CONNECTOR + variableName;
    }

    private ValidatorChain parse(Element element) {
        if (element == null) {
            return new ValidatorChain();
        }

        List<Element> elementList = element.elements();
        if (elementList != null && elementList.size() > 0) {
            ValidatorChain chain = new ValidatorChain();
            elementList.stream().forEach(e -> {
                String type = getAttributeValue("type", e);
                if (!StringUtils.isBlank(type)) {
                    chain.add(get(type).parse(e));
                }
            });

            return chain;
        }

        return new ValidatorChain();
    }
}
