package elcom.ex1.librarybooks.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;

@Profile("dev")
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "elcom.ex1.librarybooks.repository.library",
    entityManagerFactoryRef = "libraryEntityManagerFactory",
    transactionManagerRef = "libraryTransactionManager")
public class LibraryDataConfig {

    @Bean
    @ConfigurationProperties("spring.library.datasource")
    public DataSourceProperties libraryDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource libraryDataSource(){
        return libraryDataSourceProperties().initializeDataSourceBuilder().type(DriverManagerDataSource.class).build();
    }

    @Bean(name = "libraryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean libraryEntityManagerFactory(EntityManagerFactoryBuilder builder){
        return builder.dataSource(libraryDataSource()).packages("elcom.ex1.librarybooks.entity.library").build();
    }

    @Bean(name = "libraryTransactionManager")
    public PlatformTransactionManager libraryTransactionManager(
            final @Qualifier("libraryEntityManagerFactory") LocalContainerEntityManagerFactoryBean libraryEntityManagerFactory){
        return new JpaTransactionManager(libraryEntityManagerFactory.getObject());
    }

}
