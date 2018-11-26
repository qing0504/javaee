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
public class MsgConsumer {
    private static final String FILE_PATH = "rabbitmq.properties";

    private static final String QUEUE_NAME = "queue.mq.test";
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
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "test");

            channel.basicConsume(QUEUE_NAME, false, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body);
                    System.out.println(message);
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            });
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}
