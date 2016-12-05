package com.io.sample;

import com.common.utils.FileUtils;

import java.io.File;

/**
 * 文件夹拷贝
 * 递归
 * Created by Martin on 2016/12/4.
 */
public class CopyDirDemo {
    public static void main(String[] args) {
        // \(window) or /(linux)
        System.out.println("文件分隔符=>" + File.separator);
        // ;
        System.out.println("路径分隔符=>" + File.pathSeparator);
        //源文件夹路径
        String srcDir = "E:/javaee/demo/study";
        //目标文件夹路径
        String destDir = "E:/javaee/demo/dir";
        //文件夹拷贝
        FileUtils.copyDir(srcDir, destDir);
    }
}
