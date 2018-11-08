package com.validate;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author wanchongyang
 * @date 2018/11/7 9:27 PM
 * @see org.w3c.dom
 */
public class W3cDomParseXmlTest {
    private static final String PARA_NAME = "IMPORT_PARA_TEST";
    private static final String EMAIL_VARIABLE_NAME = "email";

    public static void main(String[] args) {
        try {
            //得到DOM解析器的工厂实例
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //从DOM工厂中获得DOM解析器
            DocumentBuilder db = dbf.newDocumentBuilder();
            //把要解析的xml文档读入DOM解析器
            InputStream inputStream = W3cDomParseXmlTest.class.getClassLoader().getResourceAsStream("validator-config.xml");
            Document doc = db.parse(inputStream);
            Element root = doc.getDocumentElement();

            BeanValidatorChainFactory chainFactory = new DefaultBeanValidatorChainFactory();
            BeanValidatorChain chain = chainFactory.getChain(PARA_NAME, EMAIL_VARIABLE_NAME, root);
            System.out.println(chain);
            System.out.println(chainFactory.getChain(PARA_NAME, EMAIL_VARIABLE_NAME));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
