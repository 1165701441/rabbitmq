package com.example.springbootrabitmq.customer;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/*@Component*/
public class WorkCustomer {

    //消费者1
    @RabbitListener(queuesToDeclare = @Queue(value = "work",autoDelete = "true"))
    public void receive(String message){
        System.out.println("message" +message);
    }

    //消费者2
    @RabbitListener(queuesToDeclare = @Queue(value = "work",autoDelete = "true"))
    public void receive2(String message){
        System.out.println("message2"+message);
    }
}
