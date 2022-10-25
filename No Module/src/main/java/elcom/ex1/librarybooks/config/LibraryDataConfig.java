package elcom.ex1.librarybooks.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "elcom.ex1.librarybooks.repository.library",
    entityManagerFactoryRef = "libraryEntityManagerFactory",
    transactionManagerRef = "libraryTransactionManager")
public class LibraryDataConfig {

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.library")
    public DataSourceProperties libraryDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource libraryDataSource(){
        return libraryDataSourceProperties().initializeDataSourceBuilder().type(DriverManagerDataSource.class).build();
    }

    @Bean(name = "libraryEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean libraryEntityManagerFactory( EntityManagerFactoryBuilder builder){
        return builder.dataSource(libraryDataSource()).packages("elcom.ex1.librarybooks.entity.library").build();
    }

    @Bean(name = "libraryTransactionManager")
    @Primary
    public PlatformTransactionManager libraryTransactionManager(
            final @Qualifier("libraryEntityManagerFactory") LocalContainerEntityManagerFactoryBean libraryEntityManagerFactory){
        return new JpaTransactionManager(libraryEntityManagerFactory.getObject());
    }

}
