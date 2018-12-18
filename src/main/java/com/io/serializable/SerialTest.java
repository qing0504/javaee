package com.io.serializable;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Java的序列化机制是通过在运行时判断类的serialVersionUID来验证版本一致性的。
 * 在进行反序列化时，JVM会把传来的字节流中的serialVersionUID与本地相应实体（类）的serialVersionUID进行比较，
 * 如果相同就认为是一致的，可以进行反序列化，否则就会出现序列化版本不一致的异常。(InvalidClassException)
 *
 * @author wanchongyang
 * @date 2018-11-30 18:08
 */
public class SerialTest {
    public static void main(String[] args) {
        final Serial serial1 = new Serial(1, "song");
        System.out.println("Object Serial" + serial1);
        try {
            FileOutputStream fos = new FileOutputStream("serialTest.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(serial1);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
