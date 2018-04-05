package com.pattern.singleton;

/**
 * 恶汉式单例模式
 * Created by wanchongyang on 2017/10/10.
 */
public class EagerSingleton {
    //在类加载时就完成了初始化，所以类加载较慢，但获取对象的速度快

    private static EagerSingleton instance = new EagerSingleton();//静态私有成员，已初始化

    private EagerSingleton()
    {
        //私有构造函数
    }

    public static EagerSingleton getInstance()    //静态，不用同步（类加载时已初始化，不会有多线程的问题）
    {
        return instance;
    }
}
