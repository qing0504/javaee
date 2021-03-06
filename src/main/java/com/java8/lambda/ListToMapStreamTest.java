package com.java8.lambda;

import com.alibaba.fastjson.JSON;
import com.java8.stream.User;
import com.pattern.adapter.Source;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * lambda实现的list to map
 * @author wanchongyang
 * @date 2018/4/18 下午2:37
 */
public class ListToMapStreamTest {
    public static void main(String[] args) {
        List<Account> accounts = Account.build(10);
        accounts.forEach(Account::print);

        System.out.println("===========subAccountList============");
        List<Account> subAccountList = accounts.stream().map(a -> {
            SubAccount subAccount = new SubAccount();
            subAccount.setSubName("sub " + a.getUserName());
            return subAccount;
        }).collect(Collectors.toList());
        subAccountList.stream().forEach(System.out::println);

        System.out.println("===========getIdNameMap============");
        getIdNameMap(accounts).forEach((k, v) -> System.out.println(k + " : " + v));

        System.out.println("===========getIdAccountMap============");
        getIdAccountMap(accounts).forEach((k, v) -> System.out.println(k + " : " + v.toString()));

        System.out.println("===========getNameAccountMap============");
        try {
            getNameAccountMap(accounts).forEach((k, v) -> System.out.println(k + " : " + v.toString()));
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("===========getNameAccountMap2============");
        getNameAccountMap2(accounts).forEach((k, v) -> System.out.println(k + " : " + v.toString()));

        System.out.println("===========getNameAccountMap3============");
        getNameAccountMap3(accounts).forEach((k, v) -> System.out.println(k + " : " + v.toString()));

        Map<Long, Map<String, Account>> idMap = accounts.stream().collect(
                Collectors.groupingBy(
                        Account::getId,
                        Collectors.mapping(Function.identity(), Collectors.toMap(t -> "A" + t.getId(), Function.identity()))
                )
        );

        System.out.println(JSON.toJSONString(idMap));

        Map<Long, List<Account>> idList = accounts.stream().collect(
                Collectors.groupingBy(
                        Account::getId,
                        Collectors.mapping(Function.identity(), Collectors.toList())
                )
        );

        Map<String, List<Account>> idList1 = accounts.stream().collect(
                Collectors.groupingBy(
                        a -> a.getId() + "",
                        Collectors.mapping(Function.identity(), Collectors.toList())
                )
        );
        System.out.println(JSON.toJSONString(idList));

        System.out.println("================getNameList======================");
        Map<String, List<Account>> nameList = accounts.stream().collect(
                Collectors.groupingBy(
                        Account::getUserName,
                        Collectors.mapping(Function.identity(), Collectors.toList())
                )
        );
        System.out.println(JSON.toJSONString(nameList));
    }

    /**
     * 常用方式
     * @param accounts Account集合
     * @return
     */
    public static Map<Long, String> getIdNameMap(List<Account> accounts) {
        return accounts.stream().collect(Collectors.toMap(Account::getId, Account::getUserName));
    }

    /**
     * 收集成实体本身map
     * @param accounts
     * @return
     */
    public static Map<Long, Account> getIdAccountMap(List<Account> accounts) {
        return getIdAccountMap2(accounts);
//        return accounts.stream().collect(Collectors.toMap(Account::getId, account -> account));
    }

    public static Map<Long, Account> getIdAccountMap2(List<Account> accounts) {
        return accounts.stream().collect(Collectors.toMap(Account::getId, Function.identity()));
    }

    /**
     * 重复key情况
     * 可能报错：java.lang.IllegalStateException: Duplicate key
     * @param accounts
     * @return
     */
    public static Map<String, Account> getNameAccountMap(List<Account> accounts) {
        return accounts.stream().collect(Collectors.toMap(Account::getUserName, Function.identity()));
    }

    public static Map<String, Account> getNameAccountMap2(List<Account> accounts) {
        // 后者覆盖前者
        return accounts.stream().collect(Collectors.toMap(Account::getUserName, Function.identity(), (key1, key2) -> key2));
    }

    /**
     * 指定具体收集的map
     * @param accounts
     * @return
     */
    public static Map<String, Account> getNameAccountMap3(List<Account> accounts) {
        return accounts.stream().collect(Collectors.toMap(Account::getUserName, Function.identity(), (key1, key2) -> key2, LinkedHashMap::new));
    }
}
