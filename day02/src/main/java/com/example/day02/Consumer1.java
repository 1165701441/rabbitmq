package com.example.day02;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer1 {

    public static void main(String[] args) throws IOException {
       Connection connection = RabitMQUtils.getConnection();
       Channel channel = connection.createChannel();
       //每次消费一个消息
       channel.basicQos(1);

       channel.queueDeclare("work",false,false,false,null);
       channel.basicConsume("work",false,new DefaultConsumer(channel){
           @Override
           public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
               System.out.println(new String(body));
               //手动确认
               //参数一：确认标记
               //参数二：是否开启多个消息同时确认
               channel.basicAck(envelope.getDeliveryTag(),false);
           }
       });

    }
}
