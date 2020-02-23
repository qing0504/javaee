package com.pattern.prototype;

import com.alibaba.fastjson.JSON;

/**
 * 深克隆（fastjson序列化方式）
 * @author wanchongyang
 * @date 2020/1/4 11:22 下午
 */
public class DeepCloneTest {
    public static void main(String[] args) {
        Student student = new Student();
        student.setStuNo("S001");
        student.setStuName("张三");
        System.out.println(student);
        Student cloneStudent = JSON.parseObject(JSON.toJSONString(student), Student.class);
        System.out.println(cloneStudent);
    }

}
class Student {
    private String stuNo;
    private String stuName;

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }
}
