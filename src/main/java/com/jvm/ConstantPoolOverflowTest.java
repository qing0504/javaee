package com.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试内容：常量池溢出
 * 虚拟机参数-XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10M
 * @author wanchongyang
 * @date 2018/4/26 上午10:59
 */
public class ConstantPoolOverflowTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int i = 1;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
