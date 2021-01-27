package com.example.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/*@Component*/
public class Consumer {

    @RabbitListener(queues = "order_delay_queue")
    public void ListenerQueue(Message message, Channel channel){
        //1、获取消息的id
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        System.out.println("30分钟到期取消订单");
        System.out.println("message:"+new String(message.getBody()));

        try {
            channel.basicAck(deliveryTag,false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
