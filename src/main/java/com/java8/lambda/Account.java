package com.java8.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanchongyang
 * @date 2018/4/18 下午2:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Account extends SuperAccount {
    private Long id;
    private String userName;

    private static String[] USER_NAME_ARRAY = new String[]{"bob", "martin", "joe"};

    public static List<Account> build(int len) {
        List<Account> accountList = new ArrayList<>(len);
        for (int i = 1; i <= len; i++) {
            accountList.add(new Account(Long.valueOf(i), USER_NAME_ARRAY[i%3]));
        }

        return accountList;
    }

    public static void print(Account account) {
        System.out.println(account.toString());
    }
}
