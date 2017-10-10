package com.pattern.abstractfacoty;

import com.pattern.factory.Sender;

/**
 * Created by wanchongyang on 2017/10/10.
 */
public interface Provider {
    Sender produce();
}
