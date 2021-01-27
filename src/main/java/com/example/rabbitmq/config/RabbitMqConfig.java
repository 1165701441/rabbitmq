package com.example.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


public class RabbitMqConfig {
    //定义交换机的名字
    public static final String  EXCHANGE_NAME = "order_delay_exchange";
    //定义队列的名字
    public static final String QUEUE_NAME = "order_delay_queue ";

    //1、声明交换机
    @Bean("order_delay_exchange")
    public Exchange bootExchange(){

        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }



    //2、声明队列
    @Bean("order_delay_queue")
    public Queue bootQueue(){

        return QueueBuilder.durable(QUEUE_NAME).build();
    }


    //3、队列与交换机进行绑定
    @Bean
    public Binding bindQueueExchange(@Qualifier("order_delay_queue") Queue queue, @Qualifier("order_delay_exchange") Exchange exchange){
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("boot.#")
                .noargs();
    }

}
