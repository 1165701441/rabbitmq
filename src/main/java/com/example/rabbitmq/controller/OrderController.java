package com.example.rabbitmq.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/")
    public String send(){
        rabbitTemplate.convertAndSend("order_delay_exchange", "order.cancle", "取消订单....");
       return  "111";
    }

    @GetMapping("/hh")
    public String hh(){
        //进行消息发送
        for (int i = 0; i < 10; i++) {
            // 发送消息
            rabbitTemplate.convertAndSend("order_delay_exchange", "boot.HELLO", "message...."+i);
        }
        return "hh";
    }
}
