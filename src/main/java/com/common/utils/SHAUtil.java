package com.common.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author wanchongyang
 * @date 2018/8/15 下午3:16
 */
public class SHAUtil {
    private static final String APPSECRET = "f4cc82386a1cdddcc98e4f53b1115a62";

    public static String getSha1(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));
            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byteO = md[i];
                buf[k++] = hexDigits[byteO >>> 4 & 0xf];
                buf[k++] = hexDigits[byteO & 0xf];
            }
            return new String(buf);
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static String getSha1EncodeStr(String str) {
        return DigestUtils.sha1Hex(str);
    }

    private static String createSIGN(TreeMap<String, Object> signMap) {
        return createSIGN(signMap, APPSECRET);
    }

    private static String createSIGN(TreeMap<String, Object> signMap, String appSecret) {
        String queryString = getQueryString(signMap, appSecret);

        System.out.println("query:" + queryString);
        // return SHAUtil.getSha1(queryString).toLowerCase();
        return SHAUtil.getSha1EncodeStr(queryString).toLowerCase();
    }

    /**
     * 拼接字符串
     *
     * @param params 参数map
     * @param appSecret 签名密钥
     * @return 拼接后字符串
     */
    private static String getQueryString(TreeMap<String, Object> params, String appSecret) {
        if (params == null || params.size() == 0) {
            return "";
        }

        params.put("appsecret", appSecret);
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (entry.getValue() != null && StringUtils.isNoneBlank(entry.getValue().toString())) {
                stringBuffer.append(entry.getKey().trim());
                stringBuffer.append("=");
                stringBuffer.append(entry.getValue().toString().trim());
                stringBuffer.append("&");
            }
        }

        return stringBuffer.deleteCharAt(stringBuffer.length() - 1).toString();
    }

    public static void main(String[] args) {
        testCreateSign();

        // testRecharge();

        // System.out.println("===========================");

        // testQuery();
    }

    private static void testCreateSign() {
        long timestamp = System.currentTimeMillis() / 1000;
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("appid", 20110724);
        // treeMap.put("appsecret", "09c45b61c2fb25504317006e9f810385");
        treeMap.put("orderNo", "DD1510221610171546");
        treeMap.put("timestamp", timestamp);

        String sign = createSIGN(treeMap, "09c45b61c2fb25504317006e9f810385");
        System.out.println("timestamp:" + timestamp);
        System.out.println("sign:" + sign);
    }

    private static void testQuery() {
        Map<String, Object> params = new HashMap<>(16);
        params.put("rechargeNo", "pro1534411299851");
        params.put("outerTradeNo", "10000001_NO00000000000000002");
        // params.put("outerTradeNo", "10000001_NO00000000000000001");

        long timestamp = System.currentTimeMillis() / 1000;
        String dataStr = JSON.toJSONString(params);
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("data", dataStr);
        treeMap.put("timestamp", timestamp);

        String sign = createSIGN(treeMap);
        System.out.println("data:" + dataStr);
        System.out.println("timestamp:" + timestamp);
        System.out.println("sign:" + sign);
    }

    private static void testRecharge() {
        Map<String, Object> params = new HashMap<>(16);
        params.put("name", "张三");
        params.put("idNumber", "412826198808158031");
        params.put("cardNumber", "90001123334890849");
        params.put("money", new BigDecimal(50.00));
        params.put("rechargeFlag", 1);
        params.put("outerTradeNo", "10000001_NO00000000000000002");

        long timestamp = System.currentTimeMillis() / 1000;
        String dataStr = JSON.toJSONString(params);
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("data", dataStr);
        treeMap.put("timestamp", timestamp);

        String sign = createSIGN(treeMap);
        System.out.println("data:" + dataStr);
        System.out.println("timestamp:" + timestamp);
        System.out.println("sign:" + sign);
    }

}
