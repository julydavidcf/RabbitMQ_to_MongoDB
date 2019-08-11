package com.davidcai.app;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

//Consumer has an auto delay of 5 sec.
    @Test
    public void setUpTest()  {


        try{
            Exchange exchange = new Exchange();

            exchange.createExchangeAndQueue();


            Sender produce = new Sender();
            produce.publish();


            Consumer receive = new Consumer();
            receive.receive();
        }catch(Exception e){
            e.printStackTrace();
        }

    }


}

