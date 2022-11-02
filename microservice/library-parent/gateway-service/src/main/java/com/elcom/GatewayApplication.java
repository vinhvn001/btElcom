package com.elcom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
@ComponentScan({"com.elcom"})
@EntityScan("com.elcom.id.model")
public class GatewayApplication {

    public static void main (String[] args){

        SpringApplication.run(GatewayApplication.class, args);
    }
}
