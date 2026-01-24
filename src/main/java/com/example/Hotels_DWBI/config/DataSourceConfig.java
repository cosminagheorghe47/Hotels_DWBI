package com.example.Hotels_DWBI.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.oltp")
    public DataSource oltpDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.dw")
    public DataSource dwDataSource() {
        return DataSourceBuilder.create().build();
    }
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean oltpEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(oltpDataSource())
                .packages("com.example.Hotels_DWBI.oltp.model") // modelele OLTP
                .persistenceUnit("oltp")
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean dwEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dwDataSource())
                .packages("com.example.Hotels_DWBI.dw.model") // modelele DW
                .persistenceUnit("dw")
                .build();
    }
    @Bean
    @Primary
    public PlatformTransactionManager oltpTransactionManager(
            @Qualifier("oltpEntityManagerFactory") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public PlatformTransactionManager dwTransactionManager(
            @Qualifier("dwEntityManagerFactory") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

}
