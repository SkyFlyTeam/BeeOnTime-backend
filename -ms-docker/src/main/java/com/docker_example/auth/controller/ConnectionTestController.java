package com.docker_example.auth.controller;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@RestController
@RequestMapping("/test")
public class ConnectionTestController {

    @Autowired
    // @Qualifier("postgresDataSource")  // 🔹 Agora especificamos o bean correto para PostgreSQL
    private DataSource postgresDataSource;

    @Autowired
    // @Qualifier("mysqlDataSource")  // 🔹 Agora especificamos o bean correto para MySQL
    private DataSource mysqlDataSource;

    @Autowired
    private StringRedisTemplate redisTemplate;  // Redis

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/postgres")
    public String testPostgresConnection() {
        try (Connection connection = postgresDataSource.getConnection()) {
            return connection.isValid(2) ? "PostgreSQL Conectado!" : "Erro ao conectar no PostgreSQL";
        } catch (SQLException e) {
            return "Erro ao conectar no PostgreSQL: " + e.getMessage();
        }
    }

    @GetMapping("/mysql")
    public String testMySQLConnection() {
        try (Connection connection = mysqlDataSource.getConnection()) {
            return connection.isValid(2) ? "MySQL Conectado!" : "Erro ao conectar no MySQL";
        } catch (SQLException e) {
            return "Erro ao conectar no MySQL: " + e.getMessage();
        }
    }

    @GetMapping("/redis")
    public String testRedisConnection() {
        try {
            redisTemplate.opsForValue().set("test_key", "Redis funcionando!");
            return "Redis Conectado!";
        } catch (Exception e) {
            return "Erro ao conectar no Redis: " + e.getMessage();
        }
    }

    @GetMapping("/mongo")
    public String testMongoConnection() {
        try {
            // Verifica se a coleção existe antes de criar
            if (!mongoTemplate.collectionExists("test_collection")) {
                mongoTemplate.createCollection("test_collection");
            }
            return "MongoDB Conectado!";
        } catch (Exception e) {
            return "Erro ao conectar no MongoDB: " + e.getMessage();
        }
    }
}
