package com.pattern.singleton;

/**
 * 懒汉式单例模式
 * Created by wanchongyang on 2017/10/10.
 */
public class LazySingleton3 {
    /* 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载 */
    private static volatile LazySingleton3 instance = null;

    /* 私有构造方法，防止被实例化 */
    private LazySingleton3() {
    }

    /* 静态工程方法，创建实例 */
    public static LazySingleton3 getInstance() {
        if (instance == null) {
            synchronized (LazySingleton3.class) {
                if (instance == null) {
                    instance = new LazySingleton3();
                }
            }
        }
        return instance;
    }


    /* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */
    public Object readResolve() {
        return instance;
    }
}
