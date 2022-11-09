/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.loan.messaging.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Admin
 */
@Configuration
public class RpcConfig {
    
    @Value("${loan.rpc.exchange}")
    private String rpcExchange;
    
    @Value("${loan.rpc.queue}")
    private String rpcQueue;
    
    @Value("${loan.rpc.key}")
    private String rpcKey;

    @Bean("rpcQueue")
    public Queue rpcQueue() {
        return new Queue(rpcQueue);
    }

    @Bean("rpcExchange")
    public DirectExchange rpcExchange() {
        return new DirectExchange(rpcExchange);
    }

    @Bean("rpcBinding")
    public Binding bindingRpc(DirectExchange rpcExchange, Queue rpcQueue) {
        return BindingBuilder.bind(rpcQueue).to(rpcExchange).with(rpcKey);
    }

    @Bean
    public RpcServer rpcServer() {
        return new RpcServer();
    }
}
