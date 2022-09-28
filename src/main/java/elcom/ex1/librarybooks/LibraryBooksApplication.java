package elcom.ex1.librarybooks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class })
@EnableCaching
@EnableElasticsearchRepositories(basePackages = "elcom.ex1.librarybooks.repository.elastic")
public class LibraryBooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryBooksApplication.class, args);
	}

}
