package com.example.springbootrabitmq.customer;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/*@Component*/
//如果不存在消息队列则创建hello
@RabbitListener(queuesToDeclare = @Queue(value = "hello",declare = "true",autoDelete = "true"))
public class HelloCustomer {

    @RabbitHandler
    public void receive(String message){
        System.out.println(message);
    }
}
