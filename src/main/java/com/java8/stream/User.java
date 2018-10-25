package com.java8.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wanchongyang
 * @date 2018/9/3 下午3:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Integer id;
    private List<String> name;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public static List<User> buildList(int len) {
        if (len < 1) {
            throw new IllegalArgumentException("The parameter must be greater than 0.");
        }

        List<User> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            List<String> name = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                name.add("name" + i + j);
            }
            list.add(new User(i, name));
        }

        return list;
    }

    public static void main(String[] args) {
        // List<User> => List<String>()
        List<User> userList = buildList(5);
        userList.stream().forEach(System.out::println);

        System.out.println("=========================");
        List<String> reduce = userList.stream().map(u -> u.getName()).reduce(new ArrayList<>(), (n1, n2) -> {
            n1.add(StringUtils.join(n2.toArray(), ","));
            return n1;
        });

        System.out.println(reduce);
    }
}
