package com.example.springbootrabitmq.customer;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
/*
@Component*/
public class FanoutCustomer {

    @RabbitListener(bindings=@QueueBinding(
            value=@Queue,
            exchange=@Exchange(value="logs_fanout",type=ExchangeTypes.FANOUT)
    )
    )
    public void receive(String message){
        System.out.println("fanout1模型"+message);
    }
    @RabbitListener(bindings=@QueueBinding(
            value=@Queue,
            exchange=@Exchange(value="logs_fanout",type=ExchangeTypes.FANOUT)
    )
    )
    public void receive2(String message){
        System.out.println("fanout2模型"+message);
    }
}
