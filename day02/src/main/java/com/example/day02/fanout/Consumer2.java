package com.example.day02.fanout;

import com.example.day02.RabitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("logs","fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName,"logs","");
        channel.basicConsume(queueName,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
            }
        });
    }
}
