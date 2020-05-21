package com.spectral.demoapplication.config;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class TestConfig {

    @Bean
    public DataSource dataSource() {
        EmbeddedPostgres embeddedPostgres;
        try {
            embeddedPostgres = EmbeddedPostgres.builder()
                    .setCleanDataDirectory(true)
                    .start();
        } catch (IOException e) {
            throw new IllegalArgumentException("Can’t initialize an embedded PostgreSQL");
        }
        HikariDataSource dataSource = new HikariDataSource();
        if (embeddedPostgres == null) {
            throw new IllegalArgumentException("Embedded PostgreSQL not initialised");
        }
        dataSource.setDataSource(embeddedPostgres.getPostgresDatabase());
        dataSource.setMinimumIdle(5);
        return dataSource;
    }
}
