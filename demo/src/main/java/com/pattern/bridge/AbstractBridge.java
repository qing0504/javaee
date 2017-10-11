package com.pattern.bridge;

/**
 * Created by wanchongyang on 2017/10/11.
 */
public abstract class AbstractBridge {
    private Sourceable source;

    public void method(){
        source.method();
    }

    public Sourceable getSource() {
        return source;
    }

    public void setSource(Sourceable source) {
        this.source = source;
    }
}
