package com.example.day02.fanout;

import com.example.day02.RabitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class Privider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabitMQUtils.getConnection();

        Channel channel = connection.createChannel();
        //交换机的名称，交换机的类型
        channel.exchangeDeclare("logs_fanout","fanout");
        channel.basicPublish("logs_fanout","",null,"jj".getBytes());
        RabitMQUtils.closeConnectionAndChannel(channel,connection);
    }
}
