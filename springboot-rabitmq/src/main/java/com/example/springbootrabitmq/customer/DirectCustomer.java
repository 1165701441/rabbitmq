package com.example.springbootrabitmq.customer;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectCustomer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "logs_direct",type = ExchangeTypes.DIRECT),
                    key = {"error","info"}
            )
    })
    public void receive(String message){
        System.out.println("error"+"||info"+message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "logs_direct",type = ExchangeTypes.DIRECT),
                    key = "save"
            )
    })
    public void receive2(String message){
        System.out.println("save"+message);
    }
}
