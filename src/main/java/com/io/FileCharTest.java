package com.io;

import com.common.utils.FileUtils;

import java.io.*;

/**
 * 字符流
 * 1、只能读取纯文本文件
 * 2、节点流 Reader FileReader
 *         Writer FileWriter
 *
 * @author wanchongyang
 */
public class FileCharTest {
    public static void main(String[] args) {
        //创建源
        File src = new File("E:/javaee/demo/aa.txt");
        File dest = new File("E:/javaee/demo/bb.txt");
        //选择流
        //Reader reader = null;
        //Writer writer = null;
        // 缓冲流
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(src));
            //writer = new BufferedWriter(new FileWriter(dest, true));// 追加写
            writer = new BufferedWriter(new FileWriter(dest));//覆盖写
            // 读取操作
            /*char[] flush = new char[1024];
            int len = 0;
            while (-1 != (len = reader.read(flush))){
                String str = new String(flush, 0, len);
                System.out.println(str);

                //写操作
                //writer.write(str);
                writer.write(flush, 0, len);
            }*/

            // 新增方法功能：readLine(),newLine()
            String line = null;
            while (null != (line = reader.readLine())){
                System.out.println(line);
                writer.write(line);
                writer.newLine();
            }

            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("文件不存在");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件读取失败");
        } finally {
            if(null != reader) {
                FileUtils.closeAll("关闭流异常", writer, reader);
            }
        }
    }
}
