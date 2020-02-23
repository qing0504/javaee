package com.sample;

import java.util.*;
import java.io.*;

/**
 * @author wanchongyang
 * @date 2020/2/23 11:01 下午
 */
public class CFRDecompilerDemo {

    int x = 3;

    /**
     * 字符串拼接
     * option: --stringbuilder false
     */
    public void stringBuilderTest(int end) {
        char[] foo = new char[]{'@', 'a', '*'};
        char ch;
        int x = 0;
        while ((ch = foo[++x]) != '*') {
            System.out.println("" + x + ": " + ch);
        }
    }

    /**
     * 条件编译
     * option: 不需要参数
     */
    public void ifCompilerTest() {
        if(false) {
            System.out.println("false if");
        }else {
            System.out.println("true else");
        }
    }

    /**
     * 断言, JDK1.4开始支持
     * option: --sugarasserts false
     */
    public void assertTest(String s) {
        assert (!s.equals("Fred"));
        System.out.println(s);
    }

    /**
     * 枚举与Switch语句
     * option: --decodeenumswitch false
     */
    public int switchEnumTest(EnumTest e) {
        switch (e) {
            case FOO:
                return 1;
            case BAP:
                return 2;
        }
        return 0;
    }

    /**
     * 字符串与Switch语句
     * option: --decodestringswitch false
     */
    public int switchStringTest(String s) {
        switch (s) {
            default:
                System.out.println("Test");
                break;
            case "BB":  // BB and Aa have the same hashcode.
                return 12;
            case "Aa":
            case "FRED":
                return 13;
        }
        System.out.println("Here");
        return 0;
    }

    /**
     * 可变参数
     * option: --arrayiter false
     */
    public void varargsTest(String ... arr) {
        for (String s : arr) {
            System.out.println(s);
        }
    }

    /**
     * 自动装箱/拆箱
     * option: --sugarboxing false
     */
    public Double autoBoxingTest(Integer i, Double d) {
        return d + i;
    }

    /**
     * 枚举, JDK1.5开始支持
     * option: --sugarenums false
     */
    public enum EnumTest {
        FOO,
        BAR,
        BAP
    }

    /**
     * 内部类
     * option: --removeinnerclasssynthetics false
     */
    public void innerClassTest() {
        new InnerClass().getSum(6);
    }

    public class InnerClass {
        public int getSum(int y) {
            x += y;
            return x;
        }
    }

    /**
     * 泛型擦除
     * option:
     */
    public void genericEraseTest() {
        List<String> list =  new ArrayList<String>();
    }

    /**
     * 增强for循环
     * option: --collectioniter false
     */
    public void forLoopTest() {
        String[] qingshanli = {"haha", "qingshan", "helloworld", "ceshi"};
        List<String> list =  Arrays.asList(qingshanli);
        for (Object s : list) {
            System.out.println(s);
        }
    }

    /**
     * lambda表达式
     * option: --decodelambdas false
     */
    public void lambdaTest() {
        String[] qingshanli = {"haha", "qingshan", "helloworld", "ceshi"};
        List<String> list =  Arrays.asList(qingshanli);
        // 使用lambda表达式以及函数操作
        list.forEach((str) -> System.out.print(str + "; "));
        // 在JDK8中使用双冒号操作符
        list.forEach(System.out::println);
    }

    /**
     * try-with-resources语句
     * option: --tryresources false
     */
    public void tryWithResourcesTest() throws IOException {
        try (final StringWriter writer = new StringWriter();
             final StringWriter writer2 = new StringWriter()) {
            writer.write("This is qingshanli1");
            writer2.write("this is qingshanli2");
        }
    }

    /**
     * 局部变量类型推断, JDK10开始支持
     * option: 不需要参数
     */
    public void varTest() {
        //初始化局部变量
        var string = "qingshanli";
        //初始化局部变量
        var stringList = new ArrayList<String>();
        stringList.add("九幽阴灵，诸天神魔，以我血躯，奉为牺牲。");
        stringList.add("三生七世，永堕阎罗，只为情故，虽死不悔！");
        stringList.add("blog:http://www.cnblogs.com/qingshanli/");
        //增强for循环的索引
        for (var s : stringList){
            System.out.println(s);
        }
        //传统for循环的局部变量定义
        for (var i = 0; i < stringList.size(); i++){
            System.out.println(stringList.get(i));
        }
    }
}
