package com.example.rabbitmq.config;



import org.springframework.amqp.core.*;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderMqConfig {
    //1. 定义正常交换机（order_exchange）和队列(order_queue)
    public static final String  order_delay_exchange = "order_delay_exchange"; //order_exchange
    //定义队列的名字
    public static final String order_delay_queue = "order_delay_queue"; //order_queue

    //2. 定义死信交换机（order_exchange_dlx）和队列(order_queue_dlx
    public static final String  order_delay_exchange_dlx = "order_delay_exchange_dlx";// order_exchange_dlx
    //定义队列的名字
    public static final String order_delay_queue_dlx = "order_delay_queue_dlx";//order_queue_dlx


    //1、声明交换机
    @Bean("order_delay_exchange")
    public Exchange order_exchange(){
        return ExchangeBuilder.topicExchange(order_delay_exchange).durable(true).build();
    }

    //2、声明队列
    @Bean("order_delay_queue")
    public Queue order_queue(){
        //绑定，设置正常队列过期时间为30分钟,此处用10秒代替
        return QueueBuilder.durable(order_delay_queue)
                /*.withArgument("x-dead-letter-exchange",order_delay_exchange_dlx)
                .withArgument("x-dead-letter-routing-key","dlx.order.cancel")
                .withArgument("x-message-ttl",1000)*/.build();
    }

    //3、队列与交换机进行绑定
    @Bean
    public Binding bindQueueExchange(@Qualifier("order_delay_queue") Queue queue, @Qualifier("order_delay_exchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("order.#").noargs();
    }

    //死信交换机
    @Bean("order_delay_exchange_dlx")
    public Exchange order_exchange_dlx(){
        return ExchangeBuilder.topicExchange(order_delay_exchange_dlx).durable(true).build();
    }



    //2、声明队列
    @Bean("order_delay_queue_dlx")
    public Queue order_queue_dlx(){
        return QueueBuilder.durable(order_delay_queue_dlx).build();
    }


    //3、队列与交换机进行绑定
    @Bean
    public Binding bindDlQueueExchange(@Qualifier("order_delay_queue_dlx") Queue queue, @Qualifier("order_delay_exchange_dlx") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("dlx.order.#").noargs();
    }

}
