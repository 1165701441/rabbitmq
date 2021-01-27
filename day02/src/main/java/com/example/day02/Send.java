package com.example.day02;

import com.rabbitmq.client.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/send")
public class Send {


    @GetMapping("/")
    public void sendMessage() throws IOException, TimeoutException {
        //创建连接
        ConnectionFactory factory = new ConnectionFactory();

        factory.setPort(5672);
        factory.setHost("127.0.0.1");
        factory.setUsername("root");
        factory.setPassword("root");
        //设置连接虚拟机
        /*  factory.setVirtualHost("/ems");*/


        Connection connection = factory.newConnection();

        //获取连借通道
        Channel channel = connection.createChannel();

        //通道绑定对应消息队列
        //参数一：队列名称如果队列不存在则自动创建
        //参数二：队列特性是否要持久化true:持久化
        //参数三：是否独占队列true独占
        //参数四：是否消费完自动删除true删除
        //参数五：额外添加参数
        channel.queueDeclare("ems_hello", false, false, false, null);

        //参数一：交换机名称
        //参数二：队列名称
        //参数三：传递消息的额外设置 MessageProperties.PERSISTENT_BASIC消息持久化
        //参数四：消息具体内容
        channel.basicPublish("", "ems_hello", MessageProperties.PERSISTENT_BASIC, "hello,rabbitmq".getBytes());
        channel.close();
        connection.close();
    }


    @GetMapping("/get")
    public String getMessage() throws IOException, TimeoutException {
        //创建连接
        ConnectionFactory factory = new ConnectionFactory();

        factory.setPort(5672);
        factory.setHost("127.0.0.1");
        factory.setUsername("root");
        factory.setPassword("root");
        //设置连接虚拟机
        /*  factory.setVirtualHost("/ems");*/


        Connection connection = factory.newConnection();

        //获取连借通道
        Channel channel = connection.createChannel();

        //通道绑定对象
        channel.queueDeclare("ems_hello", false, false, false, null);

        final String[] message = {""};
        //消费消息
        //参数一：队列名称
        //参数二：开启消息自动确认机制
        //消息三：消费时的回调接口
        channel.basicConsume("ems_hello", true, new DefaultConsumer(channel) {
            //参数一：
            //参数二：
            //参数三：
            //参数四：消息内容
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                message[0] = new String(body);
            }
        });
        channel.close();
        connection.close();
        return message[0];
    }
}
