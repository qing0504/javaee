package com.validate.config;

import com.validate.contract.Validator;
import org.dom4j.Element;

/**
 * 解析器顶级接口
 * @author wanchongyang
 * @date 2018/11/6 3:02 PM
 */
public interface ValidatorParser {
    Validator parse(Element element);
}
