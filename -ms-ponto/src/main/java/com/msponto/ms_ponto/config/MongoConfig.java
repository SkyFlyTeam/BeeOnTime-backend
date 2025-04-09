package com.msponto.ms_ponto.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(basePackages = "com.msponto.ms_ponto.repositorio.mongo")
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "BOTPontos";
    }

    @Override
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb+srv://admin:skyfly#2024@beeontime.c2sgf.mongodb.net/?retryWrites=true&w=majority&appName=BeeOnTime");
        // return MongoClients.create("mongodb+srv://salerno:salerno@beeontime.bsrnd1l.mongodb.net/?retryWrites=true&w=majority&appName=BeeOnTime");
    }
}

