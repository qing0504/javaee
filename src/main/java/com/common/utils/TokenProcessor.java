package com.common.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

/**
 * 生成Token的工具类
 * Created by Martin on 2017/3/8.
 */
public class TokenProcessor {

    /**
     * 单例设计模式（饿汉式，保证类的对象在内存中只有一个）
     * 1、把类的构造函数私有
     * 2、自己创建一个类的对象
     * 3、对外提供一个公共的方法，返回类的对象
     */
    private TokenProcessor() {
    }

    private static final TokenProcessor instance = new TokenProcessor();

    /**
     * 返回类的对象
     *
     * @return
     */
    public static TokenProcessor getInstance() {
        return instance;
    }

    /**
     * 生成Token
     * Token：Nv6RRuGEVvmGjB+jimI/gw==
     *
     * @return
     */
    public String makeToken() {  //checkException
        String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "";
        //数据指纹   128位长   16个字节  md5
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] md5 = md.digest(token.getBytes());
            //base64编码--任意二进制编码明文字符
            byte[] encode = Base64.getEncoder().encode(md5);
            return new String(encode, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
