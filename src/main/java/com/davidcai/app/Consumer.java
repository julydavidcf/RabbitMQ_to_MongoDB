package com.davidcai.app;

import com.mongodb.*;
import com.rabbitmq.client.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class Consumer {

    private int DELAY = 5000;


    public void receive(){

        try{

            Connection conn = Server.getConnection();
            if(conn != null){
                Channel channel = conn.createChannel();

                com.rabbitmq.client.Consumer consumer1 = new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        String message = new String(body, "UTF-8");
                        System.out.println(" Message Received Queue 1 '" + message + "'");
                        if(message!="") {
                            writeToDB(message);
                        }
                        else {
                            System.out.println("Empty data recieved");
                        }
                        System.out.println("done!");
                    }
                };
                channel.basicConsume(Exchange.QUEUE_MTL, true, consumer1);


                Thread.sleep(DELAY);
                channel.close();
                conn.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }



    }

    public void writeToDB(String message){
        MongoClient client = new MongoClient();
        String currMessage = message;
        System.out.println("in write");
        DB dbs = client.getDB("mtl");




        JSONArray jsonMessage = new JSONArray(currMessage);
        JSONObject testObj = jsonMessage.getJSONObject(0);

        if(testObj.has("health_status")) {
            DBCollection collection = dbs.getCollection("health");

            for (int i = 0; i < jsonMessage.length(); i++) {

                JSONObject jsonObj = jsonMessage.getJSONObject(i);
                int timestamp = jsonObj.getInt("timestamp");
                int health = jsonObj.getInt("health_status");
                DBObject health_data = new BasicDBObject("timestamp", timestamp)
                        .append("health_status", health);
                System.out.println(timestamp + "   " + health + "  sent!");

                collection.insert(health_data);

            }
        }
        else if (testObj.has("grades")){
            DBCollection collection = dbs.getCollection("grade");

            for (int i = 0; i < jsonMessage.length(); i++) {
                System.out.println("in grade!");

                JSONObject jsonObj = jsonMessage.getJSONObject(i);
                int timestamp = jsonObj.getInt("timestamp");
                JSONArray grades = jsonObj.getJSONArray("grades");
                JSONObject gradeobj = grades.getJSONObject(0);
                int ni = gradeobj.getInt("ni");
                int cu = gradeobj.getInt("cu");
                DBObject health_data = new BasicDBObject("timestamp", timestamp)
                        .append("grades", new BasicDBObject("ni",ni).append("cu",cu));
                System.out.println(timestamp + "   " + grades + "  sent!");

                collection.insert(health_data);

            }
        }

        else if(testObj.has("temperature")){
            DBCollection collection = dbs.getCollection("temperature");

            for (int i = 0; i < jsonMessage.length(); i++) {

                JSONObject jsonObj = jsonMessage.getJSONObject(i);
                int timestamp = jsonObj.getInt("timestamp");
                int temp = jsonObj.getInt("temperature");
                DBObject health_data = new BasicDBObject("timestamp", timestamp)
                        .append("temperature", temp);
                System.out.println(timestamp + "   " + temp + "  sent!");

                collection.insert(health_data);

            }

        }

        else {
            System.out.println("Message is not about health, grade. or temperature");
        }



    }



}
