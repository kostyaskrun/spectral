package com.spectral.demoapplication.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

/**
 * Configuration for persistence layer
 */
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.spectral.demoapplication.repository")
@EnableJpaAuditing
@EntityScan(basePackages = "com.spectral.demoapplication.model")
@Configuration
public class PersistenceConfig {

    @Bean
    public JpaTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
