package com.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wanchongyang
 * @date 2018/4/18 下午2:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {
    private Long id;
    private String userName;

    private static String[] USER_NAME_ARRAY = new String[]{"bob", "martin", "joe"};

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

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
