package com.example.springbootrabitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootRabitmqApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Test
    void testHello() {

        rabbitTemplate.convertAndSend("hello","hello world");
    }

    @Test
    void testWork(){

        for (int i = 0; i <10 ; i++) {
            rabbitTemplate.convertAndSend("work","work模型"+i);
        }

    }

    @Test
    void testFanout(){
        rabbitTemplate.convertAndSend("logs_fanout","","fanout模型");
        System.out.println("发送fanout消息");
    }

    @Test
    void testTopic(){
        rabbitTemplate.convertAndSend("logs_topic","user.log.error","topic模式");
    }


    @Test
    void testDirect(){
        rabbitTemplate.convertAndSend("logs_direct","error","logs_direct的error消息");

        rabbitTemplate.convertAndSend("logs_direct","save","logs_direct的save消息");
        rabbitTemplate.convertAndSend("logs_direct","info","logs_direct的info消息");

    }
}
