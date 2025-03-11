package com.docker_example.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class PostgreSQLConfig {

    @Bean
    @Primary
    public DataSource postgresDataSource() {
        return DataSourceBuilder.create()
                .url(System.getenv("SPRING_DATASOURCE_URL"))
                .username(System.getenv("SPRING_DATASOURCE_USERNAME"))
                .password(System.getenv("SPRING_DATASOURCE_PASSWORD"))
                .driverClassName("org.postgresql.Driver")
                .build();
    }
}
