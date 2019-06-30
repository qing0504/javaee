package com.spi.util;

/**
 * @author wanchongyang
 * @date 2019-06-30 21:02
 */
public class Holder<T> {
    private volatile T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

}

