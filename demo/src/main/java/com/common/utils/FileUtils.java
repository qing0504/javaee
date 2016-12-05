package com.common.utils;

import java.io.*;

/**
 * 文件处理工具类
 * 字节缓冲流：BufferedInputStream、BufferedOutputStream
 * 字符缓冲流：BufferedReader、BufferedWriter
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

        //文件夹
        if (dest.isDirectory()) {
            System.out.println(dest.getAbsolutePath() + "\t不是建立与文件夹同名的文件");
            return;
        }

        //2、选择流
        InputStream in = null;
        OutputStream ou = null;
        try {
            // 缓冲流，提高性能
            in = new BufferedInputStream(new FileInputStream(src));
            ou = new BufferedOutputStream(new FileOutputStream(dest));
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

    /**
     * 文件夹拷贝
     * @param srcDir 源文件夹路径
     * @param destDir 目标存储路径
     */
    public static void copyDir(String srcDir, String destDir) {
        //源文件夹File对象
        File src = new File(srcDir);
        //目标存储File对象
        File dest = new File(destDir);

        copyDirDetail(src, dest);
    }

    /**
     * 文件夹拷贝操作
     * @param src 源文件File对象
     * @param dest 目标文件File对象
     */
    private static void copyDirDetail(File src, File dest) {
        if(src.isDirectory()) {// 文件夹判断
            //确保目标文件夹存在
            dest.mkdirs();
            //获取下一级目录及文件
            for(File sub : src.listFiles()) {
                copyDirDetail(sub, new File(dest, sub.getName()));
            }
        } else if (src.isFile()) {//文件直接拷贝
            copyFile(src, dest);
        } else {
            System.out.println("未知的File对象");
        }
    }
}
