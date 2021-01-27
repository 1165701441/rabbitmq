package com.example.day02;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        Connection connection = RabitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work",false,false,false,null);
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("","work",null,(i+"hello world" ).getBytes());
            System.out.println(i);
        }
        RabitMQUtils.closeConnectionAndChannel(channel,connection);
    }
}
