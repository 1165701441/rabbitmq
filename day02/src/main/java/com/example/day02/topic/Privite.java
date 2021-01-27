package com.example.day02.topic;

import com.example.day02.RabitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;

import java.io.IOException;

public class Privite {

    public static void main(String[] args) throws IOException {
        Connection connection = RabitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("logs_topic","topic");
        //创建一个临时队列
        String queueName =  channel.queueDeclare().getQueue();
        String routeKey = "user.save";
        channel.queueBind(queueName,"logs_topic",routeKey);

        channel.basicPublish("logs_topic",routeKey,null,"topic.消息".getBytes());
        RabitMQUtils.closeConnectionAndChannel(channel,connection);
    }
}
