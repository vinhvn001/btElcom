package com.elcom.id;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.elcom")
@EnableJpaRepositories("com.elcom.id.repository")
@EntityScan("com.elcom.id.model")
public class IdServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(IdServiceApplication.class, args);
    }

}
