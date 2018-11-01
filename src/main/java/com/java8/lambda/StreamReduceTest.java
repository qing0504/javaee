package com.java8.lambda;

import com.java8.stream.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author wanchongyang
 * @date 2018/11/1 11:28 AM
 */
public class StreamReduceTest {
    public static void main(String[] args) {
        List<Account> accounts = Account.build(3);
        accounts.forEach(Account::print);

        System.out.println("===========optionalAccount============");
        Account account = new Account();
        Optional<Account> optionalAccount = accounts.stream().reduce((a1, a2) -> {
            account.setUserName(a1.getUserName() + "_" + a2.getUserName());
            return account;
        });
        optionalAccount.ifPresent(System.out::println);

        System.out.println("===========identityAccount============");
        Account identityAccount = accounts.stream().reduce(new Account(), (a1, a2) -> {
            a1.setUserName(a1.getUserName() + "_" + a2.getUserName());
            return a1;
        });
        System.out.println(identityAccount);

        System.out.println("===========reduceUser============");
        User reduceUser = accounts.stream().reduce(new User(), (u, a) -> {
            System.out.println("accumulator");
            if (u.getName() == null) {
                u.setName(new ArrayList<>());
            }

            u.getName().add(a.getUserName());
            return u;
        }, (u1, u2) -> {
            System.out.println("combiner");
            u1.getName().addAll(u2.getName());
            return u1;
        });
        System.out.println(reduceUser);
    }
}
