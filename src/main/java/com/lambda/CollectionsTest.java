package com.lambda;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by wanchongyang on 2017/8/19.
 */
public class CollectionsTest {
    public static void main(String[] args) {
        Instant begin = Instant.now();
        List<Person> list = new ArrayList<>();
        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(new Person(i, "person" + i));
            personList.add(new Person(i, "person" + i));
        }

        Instant middle = Instant.now();
        System.out.println("耗时：" + Duration.between(begin, middle).toMillis() + "毫秒");

        Collections.shuffle(list);
        Collections.shuffle(personList);

        Instant end = Instant.now();
        System.out.println("耗时：" + Duration.between(begin, end).toMillis() + "毫秒");

//     方法引用
//        Collections.sort(personList, new Comparator<Person>() {
//            @Override
//            public int compare(Person x, Person y) {
//                return x.getPersonName().compareTo(y.getPersonName());
//            }
//        });
//
//        Collections.sort(personList, (p1, p2) -> p1.getPersonName().compareTo(p2.getPersonName()) );
//
//        Collections.sort(personList, Comparator.comparing(p -> p.getPersonName()));
//
//        Collections.sort(personList, Comparator.comparing(Person::getPersonName));
//
//        正序
//        personList.sort(Comparator.comparing(Person::getPersonName));
//        反序
//        personList.sort(Comparator.comparing(Person::getPersonName).reversed());

    }
}


class Person {
    private Integer personId;
    private String personName;

    public Person() {
    }

    public Person(Integer personId, String personName) {
        this();
        this.personId = personId;
        this.personName = personName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }
}