package com.validate;


import com.common.utils.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wanchongyang
 * @date 2018/11/8 2:03 PM
 */
public abstract class AbstractBeanValidatorChainFactory implements BeanValidatorChainFactory {
    private static ConcurrentHashMap<String, BeanValidatorChain> validatorChainCache = new ConcurrentHashMap<>(64);

    private static final String CONNECTOR = "$";
    public static final String VALIDATOR_ELEMENT = "validator";
    public static final String PROPERTY_ELEMENT = "property";

    public static final String TYPE_ATTRIBUTE = "type";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String VALUE_ATTRIBUTE = "value";

    @Override
    public BeanValidatorChain getChain(String paraName, String variableName) {
        return validatorChainCache.get(getKey(paraName, variableName));
    }

    @Override
    public BeanValidatorChain getChain(String paraName) {
        return getChain(paraName, null);
    }

    @Override
    public BeanValidatorChain getChain(String paraName, String variableName, String elementText) {
        Objects.requireNonNull(elementText);

        try {
            //得到DOM解析器的工厂实例
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //从DOM工厂中获得DOM解析器
            DocumentBuilder db = dbf.newDocumentBuilder();
            //把要解析的xml文档读入DOM解析器
            InputStream inputStream = new ByteArrayInputStream(elementText.getBytes("UTF-8"));
            Document doc = db.parse(inputStream);
            Element element = doc.getDocumentElement();
            return getChain(paraName, variableName, element);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public BeanValidatorChain getChain(String paraName, String variableName, Element element) {
        BeanValidatorChain chain = this.getChain(paraName, variableName);
        if (chain != null) {
            return chain;
        }

        BeanValidatorChain newChain = doParse(element);
        chain = validatorChainCache.putIfAbsent(getKey(paraName, variableName), newChain);
        if (chain == null) {
            chain = newChain;
        }

        return chain;
    }

    public BeanValidatorChain doParse(Element element) {
        preProcessXml(element);
        BeanValidatorChain chain = parse(element);
        postProcessXml(element, chain);

        return chain;
    }

    @Override
    public void remove(String paraName) {
        validatorChainCache.keySet().stream().filter(k -> k.indexOf(paraName) > -1).forEach(k -> validatorChainCache.remove(k));
    }

    @Override
    public void remove(String paraName, String variableName) {
        validatorChainCache.remove(getKey(paraName, variableName));
    }

    protected abstract BeanValidatorChain parse(Element element);
    protected abstract void preProcessXml(Element element);
    protected abstract void postProcessXml(Element element, BeanValidatorChain chain);

    private String getKey(String paraName, String variableName) {
        Objects.requireNonNull(paraName);
        if (!StringUtil.hasLength(variableName)) {
            return paraName;
        }

        return paraName + CONNECTOR + variableName;
    }

    /**
     * Parse property sub-elements of the given bean element.
     */
    public void parsePropertyElements(Element beanEle, BeanValidator validator) {
        NodeList nl = beanEle.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (isElement(node) && nodeNameEquals(node, PROPERTY_ELEMENT)) {
                parsePropertyElement((Element) node, validator);
            }
        }
    }

    private void parsePropertyElement(Element el, BeanValidator validator) {
        String name = el.getAttribute(NAME_ATTRIBUTE);
        if (!StringUtil.hasLength(name)) {
            throw new IllegalArgumentException("Tag 'property' must have a 'name' attribute");
        }

        String value = el.getAttribute(VALUE_ATTRIBUTE);
        if (!StringUtil.hasLength(value)) {
            throw new IllegalArgumentException("Tag 'property' must have a 'value' attribute");
        }

        validator.getPropertyValues().addPropertyValue(name, value);
    }


    public String getLocalName(Node node) {
        return node.getLocalName();
    }

    /**
     * <p>The default implementation checks the supplied desired name against both
     * {@link Node#getNodeName()} and {@link Node#getLocalName()}.
     * <p>Subclasses may override the default implementation to provide a different
     * mechanism for comparing node names.
     * @param node the node to compare
     * @param desiredName the name to check for
     */
    protected boolean nodeNameEquals(Node node, String desiredName) {
        return desiredName.equals(node.getNodeName()) || desiredName.equals(getLocalName(node));
    }

    protected boolean isElement(Node node) {
        return (node instanceof Element);
    }
}
