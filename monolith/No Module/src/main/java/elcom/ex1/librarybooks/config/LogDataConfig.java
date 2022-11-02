 package elcom.ex1.librarybooks.config;

import elcom.ex1.librarybooks.entity.log.SchoolClass;
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


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "elcom.ex1.librarybooks.repository.log",
        entityManagerFactoryRef = "logEntityManagerFactory",
        transactionManagerRef = "logTransactionManager")
public class LogDataConfig {

    @Bean
    @ConfigurationProperties("app.datasource.log")
    public DataSourceProperties logDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource logDataSource(){
        return logDataSourceProperties().initializeDataSourceBuilder().type(DriverManagerDataSource.class).build();
    }


    @Bean(name = "logEntityManagerFactory")
    @Qualifier
    public LocalContainerEntityManagerFactoryBean logEntityManagerFactory(EntityManagerFactoryBuilder builder){
        return builder.dataSource(logDataSource()).packages(SchoolClass.class).build();
    }


    @Bean(name = "logTransactionManager")
    public PlatformTransactionManager logTransactionManager(
            final @Qualifier("logEntityManagerFactory") LocalContainerEntityManagerFactoryBean logEntityManagerFactory){
        return new JpaTransactionManager(logEntityManagerFactory.getObject());
    }
}

