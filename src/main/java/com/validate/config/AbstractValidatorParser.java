package com.validate.config;

import org.dom4j.Attribute;
import org.dom4j.Element;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wanchongyang
 * @date 2018/11/6 3:08 PM
 */
public abstract class AbstractValidatorParser {
    private static ConcurrentHashMap<String, ValidatorParser> validatorParserCache = new ConcurrentHashMap<>(64);

    public void register(String type, ValidatorParser validatorParser) {
        validatorParserCache.putIfAbsent(type, validatorParser);
    }

    public static ValidatorParser get(String type) {
        return validatorParserCache.get(type);
    }

    public static String getAttributeValue(String attributeName, Element element) {
        Attribute attribute = element.attribute(attributeName);
        if (attribute != null) {
            return attribute.getValue();
        }

        return null;
    }

}
