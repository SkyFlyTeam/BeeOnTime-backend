package com.api.alerta.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.api.alerta.entidade.AlertaTipo;
import com.api.alerta.repositorio.AlertaTipoRepositorio;

@Configuration
public class AlertaTipoInitializer {

    @Bean
    CommandLineRunner initDatabase(AlertaTipoRepositorio repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.saveAll(List.of(
                    new AlertaTipo("Solicitação"),
                    new AlertaTipo("Ponto"),
                    new AlertaTipo("Banco de Horas"),
                    new AlertaTipo("Férias")
                ));
            }
        };
    }
}

