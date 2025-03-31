package com.ms.solicitacao.config;

import com.ms.solicitacao.model.SolicitacaoTipo;
import com.ms.solicitacao.repository.SolicitacaoTipoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SolicitacaoTipoInitializer {

    @Bean
    CommandLineRunner initSolicitacaoTipos(SolicitacaoTipoRepository repository) {
        return args -> {
            List<String> tipos = Arrays.asList(
                "Ajuste de ponto",
                "Férias",
                "Folga",
                "Ausência",
                "Hora extra"
            );

            tipos.forEach(tipoNome -> {
                if (!repository.existsByTipoSolicitacaoNome(tipoNome)) {
                    repository.save(new SolicitacaoTipo(0, tipoNome));
                }
            });
        };
    }
}
