package com.io.sample;

/**
 * 文件分割
 *
 * @author wanchongyang
 */
public class SplitFileTest {
    public static void main(String[] args) {
        SplitFile splitFile = new SplitFile("E:/javaee/demo/java.pdf");

        System.out.println("分隔的块数：" + splitFile.getSize());

        //分隔
        splitFile.split("E:/javaee/demo");
    }
}
