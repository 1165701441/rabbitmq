package com.example.springbootrabitmq.customer;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/*@Component*/
public class TopicCustomer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "logs_topic",type = ExchangeTypes.TOPIC),
                    key = "user.log.*")})
    public void receive(String message){
        System.out.println("user.log.*"+ message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "logs_topic",type = ExchangeTypes.TOPIC),
                    key = "user.del.*")})
    public void receive2(String message){
        System.out.println("user.del.*"+message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "logs_topic",type = ExchangeTypes.TOPIC),
                    key = "*.log.*")})
    public void receive3(String message){
        System.out.println("*.log.*"+message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
            value = @Queue,
            exchange = @Exchange(value = "logs_topic",type = ExchangeTypes.TOPIC),
            key = "user.log.error")})
    public void receive4(String message){
        System.out.println(message);
    }
}
