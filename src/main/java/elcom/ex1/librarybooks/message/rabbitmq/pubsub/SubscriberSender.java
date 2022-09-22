/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elcom.ex1.librarybooks.message.rabbitmq.pubsub;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.atomic.AtomicInteger;


public class SubscriberSender {

    @Autowired
    private RabbitTemplate subscriberTemplate;

    @Autowired
    private FanoutExchange fanout;
    
    @Autowired
    private DirectExchange direct;

    AtomicInteger dots = new AtomicInteger(0);

    AtomicInteger count = new AtomicInteger(0);

  // send public all
    public void send(String message) {
        StringBuilder builder = new StringBuilder(message);
        if (dots.getAndIncrement() == 3) {
            dots.set(1);
        }
        for (int i = 0; i < dots.get(); i++) {
            builder.append('.');
        }
        builder.append(count.incrementAndGet());
        System.out.println("fanout name: " + fanout.getName() + " - subscriberTemplate : " + subscriberTemplate.toString());
        subscriberTemplate.convertAndSend(fanout.getName(), "", builder.toString());
        System.out.println(" [x] Sent '" + builder.toString() + "'");
    }
    
  // send as router
    public void sendDirect(String message) {
        StringBuilder builder = new StringBuilder(message);
        if (dots.getAndIncrement() == 3) {
            dots.set(1);
        }
        for (int i = 0; i < dots.get(); i++) {
            builder.append('.');
        }
        builder.append(count.incrementAndGet());
        System.out.println("direct name: " + direct.getName() + " - subscriberTemplate : " + subscriberTemplate.toString());
        subscriberTemplate.convertAndSend(direct.getName(), "key1", builder.toString());
        System.out.println(" [x] direct sent : key1 => '" + builder.toString() + "'");
    }
}
