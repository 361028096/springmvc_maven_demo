package com.run.mq.demo_justice;

import com.google.common.base.Strings;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author : hewei
 * @date : 2018/1/11
 */
public class NewTask {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException {
        try {
            /**
             * 创建连接连接到MabbitMQ
             */
            ConnectionFactory factory = new ConnectionFactory();
            // 设置MabbitMQ所在主机ip或者主机名
            factory.setHost("127.0.0.1");
            // 创建一个连接
            Connection connection = factory.newConnection();
            // 创建一个频道
            Channel channel = connection.createChannel();
            // 指定一个队列
            boolean durable = true;
            channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
            int prefetchCount = 1;
            //限制发给同一个消费者不得超过1条消息
            channel.basicQos(prefetchCount);
            for (int i = 0; i < 5; i++) {
                // 发送的消息
                String message = "Hello World"+ Strings.repeat(".",5-i)+(5-i);
                // 往队列中发出一条消息
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println(" [x] Sent '" + message + "'");
            }
            // 关闭频道和连接
            channel.close();
            connection.close();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}