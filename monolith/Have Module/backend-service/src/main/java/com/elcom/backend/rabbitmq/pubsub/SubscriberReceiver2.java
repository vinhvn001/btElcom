/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.backend.rabbitmq.pubsub;

import org.springframework.amqp.rabbit.annotation.RabbitListener;


public class SubscriberReceiver2 {

    @RabbitListener(queues = "#{autoDeleteQueue3.name}")
    public void receiveForSub2(String in) throws InterruptedException {
        System.out.println("instance " + SubscriberReceiver2.class + "[x] Received '" + in + "'");
    }
}
