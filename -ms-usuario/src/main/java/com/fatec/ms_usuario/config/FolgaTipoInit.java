package com.fatec.ms_usuario.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fatec.ms_usuario.entidade.FolgaTipo;
import com.fatec.ms_usuario.repositorio.FolgaTipoRepositorio;

@Configuration
public class FolgaTipoInit {
	
    @Bean
    CommandLineRunner initSolicitacaoTipos(FolgaTipoRepositorio repository) {
        return args -> {
            List<String> tipos = Arrays.asList(
                "Folga",
                "Férias"
            );

            tipos.forEach(tipoNome -> {
                if (!repository.existsByTipoFolgaNome(tipoNome)) {
                    repository.save(new FolgaTipo(tipoNome));
                }
            });
        };
    }
}