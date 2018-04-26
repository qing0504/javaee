package com.jvm;

/**
 * 栈溢出测试（递归调用导致栈深度不断增加）
 * 虚拟机参数：-Xss160k
 * @author wanchongyang
 * @date 2018/4/26 上午10:38
 */
public class StackOverflowTest {
    private int stackLength = 1;
    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        StackOverflowTest stackOverflowTest = new StackOverflowTest();
        try {

            stackOverflowTest.stackLeak();
        } catch (Exception e) {
            System.out.println("stack length:" + stackOverflowTest.stackLength);
            throw e;
        }
    }
}
