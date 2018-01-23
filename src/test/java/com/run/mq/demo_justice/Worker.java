package com.run.mq.demo_justice;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

/**
 * @author : hewei
 * @date : 2018/1/11
 */
public class Worker {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws IOException, InterruptedException {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("127.0.0.1");
            // 打开连接和创建频道，与发送端一样
            Connection connection = factory.newConnection();
            final Channel channel = connection.createChannel();

            boolean durable = true;
            // 声明队列，主要为了防止消息接收者先运行此程序，队列还不存在时创建队列。
            channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
            channel.basicQos(1);//保证一次只分发一个
            // 创建队列消费者
            final Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");

                    System.out.println(" [x] Received '" + message + "'");
                    try {
                        for (char ch: message.toCharArray()) {
                            if (ch == '.') {
                                Thread.sleep(1000);
                            }
                        }
                    } catch (InterruptedException e) {
                    } finally {
                        System.out.println(" [x] Done! at " +new Date().toLocaleString());
                        channel.basicAck(envelope.getDeliveryTag(), false);
                    }
                }
            };
            channel.basicConsume(QUEUE_NAME, false, consumer);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}