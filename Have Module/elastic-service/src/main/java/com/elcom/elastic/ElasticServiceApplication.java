package com.elcom.elastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableElasticsearchRepositories(basePackages = "com.elcom.elastic.repository")
public class ElasticServiceApplication {

    public static void main(String[]args){
        SpringApplication.run(ElasticServiceApplication.class, args);
    }
}
