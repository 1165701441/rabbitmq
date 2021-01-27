package com.example.day02.direct;

import com.example.day02.RabitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Customer2 {

    public static void main(String[] args) throws IOException {
        Connection connection = RabitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("logs_direct","direct");
        String routeKey = "info";
        String queueName = channel.queueDeclare().getQueue();
        //临时队列和交换机绑定
        channel.queueBind(queueName,"logs_direct",routeKey);
        channel.queueBind(queueName,"logs_direct","error");


        channel.basicConsume(queueName,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
            }
        });

    }
}
