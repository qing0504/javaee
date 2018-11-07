package com.validate;

import com.validate.support.ValidatorChain;
import com.validate.support.ValidatorParserHelper;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * 使用dom4j解析xml
 * @author wanchongyang
 * @date 2018/11/6 10:55 AM
 */
public class ParseXmlTest {
    private static final String PARA_NAME = "IMPORT_PARA_TEST";
    public static void main(String[] args) throws DocumentException {
        InputStream inputStream = ParseXmlTest.class.getClassLoader().getResourceAsStream("validator-config.xml");
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();
        // parse(root);

        ValidatorChain chain = ValidatorParserHelper.getChain(PARA_NAME, root);
        System.out.println(chain);
        System.out.println(ValidatorParserHelper.getChain(PARA_NAME));

    }

    private static void parse(Element element) {
        if (element == null) {
            return;
        }

        List<Element> elements = element.elements();
        if (elements != null && elements.size() > 0) {
            elements.stream().forEach(e -> {
                List<Attribute> attributeList = e.attributes();
                attributeList.stream().forEach(a -> {
                    System.out.println(e.getName() + " => " + a.getName() + " => " + a.getValue());
                });
                parse(e);
            });
        }
    }
}
