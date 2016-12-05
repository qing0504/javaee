package com.io.sample;

import java.io.*;

/**
 * 转换流
 * 输入流：InputStreamReader 解码
 * 输出流：OutputStreamWriter 编码
 *
 * @author wanchongyang
 */
public class ConvertTest {
    public static void main(String[] args) throws IOException {
        //指定字符集
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                new File("E:/javaee/demo/FileUtils.java")
                        ),
                        "utf-8"
                )
        );

        BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(
                                new File("E:/javaee/demo/encode.java")
                        ),
                        "utf-8"
                )
        );

        //读取
        String line = null;
        while (null != (line = br.readLine())){
            System.out.println(line);

            bw.write(line);
            bw.newLine();
        }

        //关闭流
        bw.close();
        br.close();
    }
}
