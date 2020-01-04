package com.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 读取Properties配置文件工具类
 *
 * @author wanchongyang
 * @date 2020/1/2 11:03 下午
 */
public class PropertiesUtil {
    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private static Map<String, Properties> map = new HashMap<>();

    /**
     * 得到配置中的值
     *
     * @param key
     *            键值
     * @return
     */
    public static String getProperty(String key, String fileName) {
        Properties pro = getConfigFile(fileName);
        String keyVal = null;
        try {
            keyVal = pro.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getProperty | param key = " + key + ", fileName = " + fileName + " | error : " + e);
        }

        return keyVal;
    }

    /**
     * 得到classes下面的属性文件
     *
     * @param fileName
     * @return
     */
    public static Properties getConfigFile(String fileName) {
        Properties pro = null;
        try {
            if (map.containsKey(fileName)) {
                return map.get(fileName);
            } else {
                pro = new Properties();
                String realFileName = fileName + ".properties";
                // 1）从gconf配置服务获取项目配置文件信息
                logger.info("Loading config file: {}", realFileName);
                // 2）直接从classpath读取配置信息
                pro.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(realFileName), "UTF-8"));

                map.put(fileName, pro);
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("load config file error, error : " + e);
        }
        return pro;
    }
}
