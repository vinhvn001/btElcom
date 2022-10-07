package elcom.ex1.librarybooks;
import elcom.ex1.librarybooks.email.MailSending;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableCaching
@EnableElasticsearchRepositories(basePackages = "elcom.ex1.librarybooks.repository.elastic")
@EnableScheduling
/*@EnableAutoConfiguration(exclude = {
		DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class })*/
public class LibraryBooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryBooksApplication.class, args);
	}

	@Autowired
	private MailSending mailSending;


//	@EventListener(ApplicationReadyEvent.class)
//	public void sendMail(){
//		mailSending.sendReportMail();}

}
