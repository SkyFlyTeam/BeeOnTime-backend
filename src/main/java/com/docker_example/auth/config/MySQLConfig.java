package com.docker_example.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;

@Configuration
public class MySQLConfig {

    @Bean(name = "mysqlDataSource")
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create()
                .url(System.getenv("SPRING_DATASOURCE_MYSQL_URL"))
                .username(System.getenv("SPRING_DATASOURCE_MYSQL_USERNAME"))
                .password(System.getenv("SPRING_DATASOURCE_MYSQL_PASSWORD"))
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }
}
