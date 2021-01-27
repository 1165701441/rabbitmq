package com.example.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitmqApplicationTests {


    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    void contextLoads() {
        rabbitTemplate.convertAndSend("order-delay-exchange", "order.cancle", "取消订单....");
    }

}
