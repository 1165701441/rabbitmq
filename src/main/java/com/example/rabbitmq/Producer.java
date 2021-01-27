package com.example.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Producer {


    @RabbitListener(queues = "order_delay_queue")
    public void ListenerQueue(Message message, Channel channel) throws Exception {
        //获取到的消息
        //1、获取消息的id
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        System.out.println(deliveryTag);
        System.out.println(new String(message.getBody()));
        Thread.sleep(1000);
    }
}


