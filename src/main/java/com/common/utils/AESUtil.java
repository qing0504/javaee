package com.common.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * AES加、解密工具类
 * @author wanchongyang
 * @date 2018/09/17
 */
public class AESUtil {

    private static final String KEY_ALGORITHM = "AES";
    /**
     * 默认加密算法
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES";

    /**
     * AES 加密操作
     *
     * @param plainText  待加密内容
     * @param secretKey 加密密码
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(final String plainText, final String secretKey) {
        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            byte[] byteContent = plainText.getBytes("utf-8");

            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(secretKey));

            // 加密
            byte[] result = cipher.doFinal(byteContent);

            //通过Base64转码返回
            return Base64.encodeBase64String(result);
        } catch (Exception ex) {
            Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * AES 解密操作
     *
     * @param cipherText 待解密内容
     * @param secretKey 解密密码
     * @return 明文
     */
    public static String decrypt(String cipherText, String secretKey) {

        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(secretKey));

            //执行操作
            byte[] result = cipher.doFinal(Base64.decodeBase64(cipherText));

            return new String(result, "utf-8");
        } catch (Exception ex) {
            Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static SecretKeySpec getSecretKey(final String password) {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;

        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);

            //AES 要求密钥长度为 128
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(password.getBytes());
            kg.init(128, random);

            //生成一个密钥
            SecretKey secretKey = kg.generateKey();

            // 转换为AES专用密钥
            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static void main(String[] args) {
        String secureKey = "123456";
        String content = "{'couponId':28840953,'resourcesId':610,'resourcesType':1,'sourceType':'coupon','timestamp':1537182594}";
        String cipherText = AESUtil.encrypt(content, secureKey);
        System.out.println(cipherText);
        String plainText = AESUtil.decrypt(cipherText, secureKey);
        System.out.println(plainText);
    }
}
