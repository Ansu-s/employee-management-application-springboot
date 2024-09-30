package com.example.employee.configurations;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@Slf4j
public class FlywayConfiguration {

    @Autowired
    private DataSource dataSource;

    // Comment this if you want to run the integration tests using another database, Not disabling this will cause errors
    @PostConstruct
    public void init() {
        log.info("Migrations started");
        migrations(dataSource);
        log.info("Migrations ended");
    }

    private void migrations(DataSource dataSource) {
        Flyway flyway = Flyway
                .configure()
                .dataSource(dataSource)
                .locations("classpath:/database/migrations")
                .load();

        flyway.migrate();
    }
}
