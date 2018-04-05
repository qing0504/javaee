package com.io;

import com.common.utils.FileUtils;

/**
 * 文件拷贝 程序为桥梁，字节流
 * 1、建立联系 File对象 源头 目的地
 * 2、选择流
 * 3、操作
 * 4、释放资源：关闭
 * @author wanchongyang
 */
public class CopyFileDemo {

    public static void main(String[] args) {
        // 文件拷贝
        String srcPath = "E:/javaee/demo/test1.jpg";
        String destPath = "E:/javaee/demo/test2.jpg";
        FileUtils.copyFile(srcPath, destPath);
    }
}
