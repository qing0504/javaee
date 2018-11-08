package com.validate;

import com.common.utils.StringUtil;
import com.validate.constant.ValidatorConstant;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Optional;

/**
 * @author wanchongyang
 * @date 2018/11/8 2:06 PM
 */
public class DefaultBeanValidatorChainFactory extends AbstractBeanValidatorChainFactory {
    @Override
    public BeanValidatorChain parse(Element element) {
        return parseValidatorElements(element);
    }

    @Override
    protected void preProcessXml(Element element) {

    }

    @Override
    protected void postProcessXml(Element element, BeanValidatorChain chain) {
        // 验证所有Validator属性值是否有效
        chain.getChain().stream().forEach(BeanValidator::doVerifyMetadata);
    }

    public BeanValidatorChain parseValidatorElements(Element root) {
        NodeList nl = root.getElementsByTagName(VALIDATOR_ELEMENT);
        BeanValidatorChain beanValidatorChain = new BeanValidatorChain();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (isElement(node)) {
                Element el = (Element) node;
                String type = el.getAttribute(TYPE_ATTRIBUTE);
                if (!StringUtil.hasLength(type)) {
                    throw new IllegalArgumentException("Tag 'validator' must have a 'type' attribute.");
                }

                // type值合法性验证
                if (!ValidatorConstant.contains(type)) {
                    throw new IllegalArgumentException("illegal 'type' attribute value.");
                }

                BeanValidator beanValidator = BeanValidatorFactory.newInstance(type);
                parsePropertyElements(el, beanValidator);
                beanValidatorChain.add(beanValidator);
            }
        }

        return beanValidatorChain;
    }
}
