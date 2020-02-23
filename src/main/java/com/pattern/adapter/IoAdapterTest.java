package com.pattern.adapter;

import java.io.*;

/**
 * @author wanchongyang
 * @date 2020/2/21 2:08 下午
 */
public class IoAdapterTest {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("temp.xlsx");
        //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        // 备注上面这个初始化过程就是多次使用包装来完成的,不推荐这么写,会让新手看不懂。

        //1、获得字节输入流
        FileInputStream fileInputStream = new FileInputStream(file);
        //2、构造转换流(是继承Reader的)
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        //3、 构造缓冲字符流
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


        //备注1、2两步骤体现出了适配器模式
        //2步骤体现了InputStreamReader类具有将子节输入流转换为字符输入流的功能
        //2、3两步骤体现了装饰模式(wrapper包装模式)
    }
}
