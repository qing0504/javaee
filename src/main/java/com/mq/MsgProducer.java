package com.mq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

/**
 * @author wanchongyang
 * @date 2018/11/23 2:31 PM
 */
public class MsgProducer {
    private static final String FILE_PATH = "rabbitmq.properties";
    private static final String EXCHANGE_NAME = "exchange.mq.test";

    public static void main(String[] args) throws IOException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Properties properties = new Properties();
        InputStream in = MsgProducer.class.getClassLoader().getResourceAsStream(FILE_PATH);
        properties.load(in);
        ConnectionFactoryConfigurator.load(connectionFactory, properties);

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true, false, null);

            String body = "Hello world!";
            byte[] messageBodyBytes = body.getBytes();
            channel.basicPublish(EXCHANGE_NAME, "test", true, MessageProperties.PERSISTENT_TEXT_PLAIN, messageBodyBytes);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}
