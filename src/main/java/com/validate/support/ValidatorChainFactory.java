package com.validate.support;

import com.validate.config.AbstractValidatorParser;
import com.validate.config.EmailValidatorParser;
import com.validate.config.IntRangeValidatorParser;
import com.validate.config.RegexValidatorParser;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ValidatorChain 工厂类
 *
 * @see com.validate.support.ValidatorChain
 * @author wanchongyang
 * @date 2018/11/6 5:03 PM
 */
public class ValidatorChainFactory extends AbstractValidatorParser {
    private static ConcurrentHashMap<String, ValidatorChain> validatorChainCache = new ConcurrentHashMap<>(64);

    static {
        new IntRangeValidatorParser();
        new EmailValidatorParser();
        new RegexValidatorParser();
    }

    public static ValidatorChain getChain(String paraName) {
        return validatorChainCache.get(paraName);
    }

    public static ValidatorChain getChain(String paraName, Element element) {
        ValidatorChain chain = getChain(paraName);
        if (chain != null) {
            return chain;
        }

        ValidatorChain newChain = parse(element);
        chain = validatorChainCache.putIfAbsent(paraName, newChain);
        if (chain == null) {
            chain = newChain;
        }

        return chain;
    }

    private static ValidatorChain parse(Element element) {
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
