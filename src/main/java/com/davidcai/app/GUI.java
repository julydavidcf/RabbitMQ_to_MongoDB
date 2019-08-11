package com.davidcai.app;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JButton SendBT;
    private JPanel panel1;
    private JButton RecieveBT;

    public GUI() {
        SendBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Exchange exchange = new Exchange();

                exchange.createExchangeAndQueue();


                Sender produce = new Sender();
                produce.publish();
                JOptionPane.showMessageDialog(null,"message sent!");
            }
        });
        RecieveBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Consumer receive = new Consumer();
                receive.receive();
                JOptionPane.showMessageDialog(null,"data received!");
            }
        });
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("RabbitMQ_to_MongoDB");
        frame.setContentPane(new GUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
