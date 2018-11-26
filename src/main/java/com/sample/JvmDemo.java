package com.sample;

import java.io.*;

/**
 * @author wanchongyang
 * @date 2018/11/23 1:46 PM
 */
public class JvmDemo {
    public static void main(String[] args) {
        // 编译 javac JvmDemo.java
        // 查看字节码 javap -c -p JvmDemo.class
        String content = "中华人民共和国";
        try (InputStream is = new ByteArrayInputStream(content.getBytes("UTF-8"))) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static <T extends Serializable> T deepClone(T src) {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ByteArrayInputStream byteIn;
        ObjectInputStream in;
        T dest = null;
        try (ObjectOutputStream out = new ObjectOutputStream(byteOut)) {
            out.writeObject(src);

            //分配内存，写入原始对象，生成新对象
            byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            in = new ObjectInputStream(byteIn);

            //返回生成的新对象
            dest = (T) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return dest;

    }
}
