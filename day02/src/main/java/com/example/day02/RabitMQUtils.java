package com.example.day02;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabitMQUtils {

    public static Connection getConnection() {

        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();

            connectionFactory.setPassword("root");
            connectionFactory.setUsername("root");
            connectionFactory.setHost("127.0.0.1");
            connectionFactory.setPort(5672);
            Connection connection = connectionFactory.newConnection();
            return connection;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnectionAndChannel(Channel channel, Connection connection) {
        try {
            if (channel != null) channel.close();


            if (connection != null) connection.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }


    }
}
