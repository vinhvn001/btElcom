package elcom.ex1.librarybooks.config;


import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;


@Configuration
@EnableElasticsearchRepositories(basePackages = "elcom.ex1.librarybooks.entity.elastic")
@ComponentScan(basePackages = "elcom.ex1.librarybooks")
public class ElasticConfig extends AbstractElasticsearchConfiguration {

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();
        return RestClients.create(clientConfiguration).rest();
    }
/*
    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(restHighLevelClient());
    }

    @Bean(destroyMethod = "close")
    public RestClient restClient() {
        return restHighLevelClient().getLowLevelClient();
    }*/
}
