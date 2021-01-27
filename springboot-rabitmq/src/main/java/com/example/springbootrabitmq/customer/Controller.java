package com.example.springbootrabitmq.customer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("controller")
public class Controller {

    @Autowired
    RabbitTemplate rabbitTemplate;
    @GetMapping("fanout")
    public String fanoutText(){
        rabbitTemplate.convertAndSend("logs_fanout","","logs_fanout的消息");
        return "fanout";
    }
}
