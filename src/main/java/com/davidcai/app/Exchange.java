package com.davidcai.app;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Exchange {

    public static String EXCHANGE_NAME = "mtl-exchange";
    public static String QUEUE_MTL = "mtl-queue";
//    public static String QUEUE_GRADE = "mtl-grade";
//    public static String QUEUE_TEMPERATURE = "mtl-temperature";

    public static String ROUTING_MTL = "mtl.*.#";
//    public static String ROUTING_GRADE = "*.grade";
//    public static String ROUTING_TEMPREARTURE = "*.temperature";

    public static String ROUTING_KEY_HEALTH = "mtl.health";
    public static String ROUTING_KEY_GRADE = "mtl.grade";
    public static String ROUTING_KEY_TEMPERATURE = "mtl.temperature";

    public void createExchangeAndQueue(){
        try{
            Connection conn = Server.getConnection();
            if(conn != null){
                Channel channel = conn.createChannel();
                channel.exchangeDeclare(EXCHANGE_NAME, "topic", true);


                channel.queueDeclare(QUEUE_MTL, true, false, false, null);
                channel.queueBind(QUEUE_MTL, EXCHANGE_NAME, ROUTING_MTL);



                channel.close();
                conn.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}