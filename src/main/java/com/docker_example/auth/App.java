package com.docker_example.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.docker_example.auth")  // 🔹 Força a detecção dos controllers
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
