package com.common.utils;

import java.io.*;

/**
 * 文件处理工具类
 * Created by Martin on 2016/12/4.
 */
public class FileUtils {
    /**
     * 文件拷贝
     * @param srcPath 源文件路径
     * @param destPath 目标文件路径
     */
    public static void copyFile(String srcPath, String destPath) {
        //1、建立联系 源（存在且为文件） 目的地（可以不存在）
        copyFile(new File(srcPath), new File(destPath));
    }

    /**
     * 文件拷贝
     * @param src 源文件File对象
     * @param dest 目标文件File对象
     */
    public static void copyFile(File src, File dest) {
        //文件验证
        if (!src.isFile()) {
            System.out.println("不是文件");
            return;
        }
        //2、选择流
        InputStream in = null;
        OutputStream ou = null;
        try {
            in = new FileInputStream(src);
            ou = new FileOutputStream(dest);
            //3、文件拷贝 循环+读取+写出
            byte[] flush = new byte[1024];
            int len = 0;
            //读取
            while (-1 != (len = in.read(flush))) {
                //写出
                ou.write(flush, 0, len);
            }

            //强制刷出
            ou.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("文件不存在");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常，拷贝文件失败");
        } finally {
            //关闭，先打开的后关闭
            try {
                if (ou != null){
                    ou.close();
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("关闭流异常");
            }
        }
    }
}
