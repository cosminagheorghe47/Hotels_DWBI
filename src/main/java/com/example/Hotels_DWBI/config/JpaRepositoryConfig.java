package com.example.Hotels_DWBI.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
public class JpaRepositoryConfig {

    @EnableJpaRepositories(
            basePackages = "com.example.Hotels_DWBI.oltp.repository",
            entityManagerFactoryRef = "oltpEntityManagerFactory",
            transactionManagerRef = "oltpTransactionManager"
    )
    static class OltpJpaConfig {}

    @EnableJpaRepositories(
            basePackages = "com.example.Hotels_DWBI.dw.repository",
            entityManagerFactoryRef = "dwEntityManagerFactory",
            transactionManagerRef = "dwTransactionManager"
    )
    static class DwJpaConfig {}
}
