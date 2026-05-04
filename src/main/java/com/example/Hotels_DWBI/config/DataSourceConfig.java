package com.example.Hotels_DWBI.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

    @Bean(name = "oltpUserDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.oltp-user")
    public DataSource oltpUserDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "oltpS3DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.oltp-s3")
    public DataSource oltpS3DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "oltpUeS1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.oltp-ue-s1")
    public DataSource oltpUeS1DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "oltpUeS2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.oltp-ue-s2")
    public DataSource oltpUeS2DataSource() {
        return DataSourceBuilder.create().build();
    }

//    @Bean(name = "dwDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.dw")
//    public DataSource dwDataSource() {
//        return DataSourceBuilder.create().build();
//    }

    @Bean
    public JdbcTemplate oltpUserJdbcTemplate(@Qualifier("oltpUserDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public JdbcTemplate oltpS3JdbcTemplate(@Qualifier("oltpS3DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public JdbcTemplate oltpUeS1JdbcTemplate(@Qualifier("oltpUeS1DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public JdbcTemplate oltpUeS2JdbcTemplate(@Qualifier("oltpUeS2DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean oltpEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("oltpUserDataSource") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.Hotels_DWBI.oltp.model")
                .persistenceUnit("oltp")
                .build();
    }

//    @Bean
//    public LocalContainerEntityManagerFactoryBean dwEntityManagerFactory(
//            EntityManagerFactoryBuilder builder,
//            @Qualifier("dwDataSource") DataSource dataSource
//    ) {
//        return builder
//                .dataSource(dataSource)
//                .packages("com.example.Hotels_DWBI.dw.model")
//                .persistenceUnit("dw")
//                .build();
//    }

    @Bean
    @Primary
    public PlatformTransactionManager oltpTransactionManager(
            @Qualifier("oltpEntityManagerFactory") EntityManagerFactory emf
    ) {
        return new JpaTransactionManager(emf);
    }

//    @Bean
//    public PlatformTransactionManager dwTransactionManager(
//            @Qualifier("dwEntityManagerFactory") EntityManagerFactory emf
//    ) {
//        return new JpaTransactionManager(emf);
//    }
}
