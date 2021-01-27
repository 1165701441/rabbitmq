package com.example.day02.direct;

import com.example.day02.RabitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class privite {
    public static void main(String[] args) throws IOException {
       Connection connection = RabitMQUtils.getConnection();
       Channel channel = connection.createChannel();
       //声明交换机
       channel.exchangeDeclare("logs_direct","direct");

       String routeKey = "error";

       channel.basicPublish("logs_direct",routeKey,null,(routeKey+"这是一个directkey").getBytes());

        RabitMQUtils.closeConnectionAndChannel(channel,connection);
    }
}
