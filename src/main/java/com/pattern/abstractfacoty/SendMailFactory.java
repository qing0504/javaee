package com.pattern.abstractfacoty;

import com.pattern.factory.MailSender;
import com.pattern.factory.Sender;

/**
 * Created by wanchongyang on 2017/10/10.
 */
public class SendMailFactory implements Provider {
    @Override
    public Sender produce() {
        return new MailSender();
    }
}
