package com.sample;

import com.common.utils.PropertiesUtil;

import java.util.Properties;

/**
 * @author wanchongyang
 * @date 2020/1/2 11:07 下午
 */
public class PropertiesUtilTest {
    public static void main(String[] args) {
        String fileName = "constant";
        Properties p = PropertiesUtil.getConfigFile(fileName);
        String property = p.getProperty("third.name");
        System.out.println(property);
        System.out.println(p);
    }
}
