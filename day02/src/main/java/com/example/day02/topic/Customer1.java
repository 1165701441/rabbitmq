package com.example.day02.topic;

import com.example.day02.RabitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Customer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("logs_topic","topic");
        String queueName = channel.queueDeclare().getQueue();
        String routeKey = "user.save";
        channel.queueBind(queueName,"logs_topic",routeKey);
        channel.basicConsume(queueName,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
            }
        });

    }
}
