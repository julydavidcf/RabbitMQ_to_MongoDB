package com.davidcai.app;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Server {

    public static Connection getConnection(){
        Connection connection = null;
        try{
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            connection = factory.newConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
        return connection;
    }

}