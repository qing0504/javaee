package com.lambda;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Collectors示例
 *
 * @author wanchongyang
 * @date 2018/6/26 上午9:35
 */
public class CollectorsTest2 {
    public static void main(String[] args) {
        // 生成测试数据
        List<Account> accounts = Account.build(10);
        accounts.forEach(Account::print);
        accounts.add(new Account(7L, "joe"));
        accounts.add(new Account(7L, "tiger"));

        Map<Long, SubAccount> subAccountMap = accounts.stream().collect(
                Collectors.toMap(
                        Account::getId,
                        a -> new SubAccount(a.getUserName() + "_" + a.getId()),
                        (b, c) -> {
                            System.out.println("first:" + b + ", second:" + c);
                            b.setSubName(b.getSubName() + "&" + c.getSubName());
                            return b;
                        }
                )
        );

        System.out.println("==============SubAccount=============");
        subAccountMap.forEach((k, v) -> {
            System.out.println("k:" + k + ", v:" + v);
        });

        Map<Long, SuperAccount> superAccountMap = accounts.stream().collect(
                Collectors.toMap(
                        Account::getId,
                        // a -> new SuperAccount(a.getUserName() + "_" + a.getId()),
                        Function.identity(),
                        (b, c) -> {
                            System.out.println("first:" + b + ", second:" + c);
                            return c;
                        }
                )
        );
        System.out.println("==============SuperAccount=============");
        superAccountMap.forEach((k, v) -> {
            System.out.println("k:" + k + ", v:" + v);
        });
    }
}
