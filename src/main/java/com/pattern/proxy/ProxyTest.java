package com.pattern.proxy;

import com.pattern.decorator.Sourceable;

/**
 * Created by wanchongyang on 2017/10/11.
 */
public class ProxyTest {
    public static void main(String[] args) {
        Sourceable source = new Proxy();
        source.method();
    }
}
