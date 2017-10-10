package com.pattern.prototype;

import java.io.*;
import java.util.Date;

/**
 * 原型模式
 * Created by wanchongyang on 2017/10/10.
 */
public class Prototype implements Cloneable, Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private Date time;

    public Prototype(String name, Date time) {
        this.name = name;
        this.time = time;
    }

    /* 浅复制 */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /* 深复制 */
    public Object deepClone() throws IOException, ClassNotFoundException {

        /* 写入当前对象的二进制流 */
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);

        /* 读出二进制流产生的新对象 */
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return ois.readObject();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
