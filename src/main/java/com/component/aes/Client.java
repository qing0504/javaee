package com.component.aes;

import com.component.aes.model.Account;
import com.component.aes.model.BankInfo;
import com.component.aes.model.EncryptAccount;
import com.component.aes.model.EncryptBankInfo;

import java.math.BigDecimal;

/**
 * @author wanchongyang
 * @date 2018/5/6 下午3:10
 */
public class Client {
    public static void main(String[] args) {
        // 注解作用到类的属性上
        Account account = new Account("基本账户", new BigDecimal("100.99"), "666666666666");
        account.setId(1);
        EncryptAccount encryptAccount = BeanConverter.toEncrypt(account, EncryptAccount.class, "123");
        System.out.println(encryptAccount);

        Account decryptAccount = BeanConverter.toDecrypt(encryptAccount, Account.class, "123");
        System.out.println(decryptAccount);
        System.out.println("==========================");

        // 注解作用到类上
        BankInfo bankInfo = new BankInfo(1, "ICBC", "888888888", "张三", 2);
        EncryptBankInfo encryptBankInfo = BeanConverter.toEncrypt(bankInfo, EncryptBankInfo.class, "hello");
        System.out.println(encryptBankInfo);

        BankInfo decryptBankInfo = BeanConverter.toDecrypt(encryptBankInfo, BankInfo.class, "hello");
        System.out.println(decryptBankInfo);
    }
}
