package com.component.aes;

import com.component.aes.model.Account;
import com.component.aes.model.BankInfo;
import com.component.aes.model.EncryptAccount;
import com.component.aes.model.EncryptBankInfo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * @author wanchongyang
 * @date 2018/5/6 下午3:10
 */
public class Client {
    /**
     * AES密钥加盐
     */
    public static final String SECURE_KEY = "edb7e790-6a63-4f52-bad1-6168493973e8";

    public static void main(String[] args) {
        // 注解作用到类的属性上
        Account account = new Account("基本账户", new BigDecimal("100.99"), "666666666666");
        account.setId(1);
        account.setTimeCreated(new Date(System.currentTimeMillis()));
        account.setTimeModified(new Date(System.currentTimeMillis()));
        EncryptAccount encryptAccount = BeanConverter.toEncrypt(account, EncryptAccount.class, getSecureKey("123"));
        System.out.println(encryptAccount);

        Account decryptAccount = BeanConverter.toDecrypt(encryptAccount, Account.class, getSecureKey("123"));
        System.out.println(decryptAccount);
        System.out.println("==========================");

        // 注解作用到类上
        BankInfo bankInfo = new BankInfo(1, "ICBC", "888888888", "张三", 2);
        EncryptBankInfo encryptBankInfo = BeanConverter.toEncrypt(bankInfo, EncryptBankInfo.class, getSecureKey("hello"));
        System.out.println(encryptBankInfo);

        BankInfo decryptBankInfo = BeanConverter.toDecrypt(encryptBankInfo, BankInfo.class, getSecureKey("hello"));
        System.out.println(decryptBankInfo);
    }

    private static String getSecureKey(String password) {
        Objects.requireNonNull(password);

        return SECURE_KEY + password;
    }
}
