package com.davidcai.app;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Sender {

    String Hmessage = "";
    String Gmessage = "";
    String Tmessage = "";



    public void publish(){
        try{
            Connection conn = Server.getConnection();
            if(conn != null){
                Channel channel = conn.createChannel();
                readFile();


                channel.basicPublish(Exchange.EXCHANGE_NAME, Exchange.ROUTING_KEY_HEALTH, null, Hmessage.getBytes());
                System.out.println(" Message Sent '" + Hmessage + "'");


                channel.basicPublish(Exchange.EXCHANGE_NAME, Exchange.ROUTING_KEY_GRADE, null, Gmessage.getBytes());
                System.out.println(" Message Sent '" + Gmessage + "'");

                channel.basicPublish(Exchange.EXCHANGE_NAME, Exchange.ROUTING_KEY_TEMPERATURE, null, Tmessage.getBytes());
                System.out.println(" Message Sent '" + Tmessage + "'");

                channel.close();
                conn.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public void readFile(){
        JSONParser jsonParser = new JSONParser();
        try(FileReader reader = new FileReader("docs\\mtl_temperature.json")){
            Object obj = jsonParser.parse(reader);
            Tmessage = String.valueOf( obj);
            System.out.println(Tmessage);

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try(FileReader reader = new FileReader("docs\\mtl_health.json")){
            Object obj = jsonParser.parse(reader);
            Hmessage = String.valueOf( obj);
            System.out.println(Hmessage);

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try(FileReader reader = new FileReader("docs\\mtl_grade.json")){
            Object obj = jsonParser.parse(reader);
            Gmessage = String.valueOf( obj);
            System.out.println(Gmessage);

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


}
