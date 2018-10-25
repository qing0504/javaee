package com.java8.lambda;

/**
 * @author wanchongyang
 * @date 2018/9/21 上午9:56
 */
public class Person {
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
