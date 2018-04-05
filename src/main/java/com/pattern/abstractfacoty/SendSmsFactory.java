package com.pattern.abstractfacoty;

import com.pattern.factory.Sender;
import com.pattern.factory.SmsSender;

/**
 * Created by wanchongyang on 2017/10/10.
 */
public class SendSmsFactory implements Provider {
    @Override
    public Sender produce() {
        return new SmsSender();
    }
}
