package com.elcom.backend;

import com.elcom.backend.email.MailSending;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@ComponentScan(basePackages = { "com.elcom" })
@EnableCaching
@EnableScheduling
//@EntityScan("com.elcom.data.model.library")
public class ServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(ServiceApplication.class, args);
    }

    @Autowired
    private MailSending mailSending;

}
