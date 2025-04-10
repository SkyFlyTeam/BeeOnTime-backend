package com.ms.banco_horas.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ms.banco_horas.model.TipoCompensacao;
import com.ms.banco_horas.repository.TipoCompensacaoRepository;

@Configuration
public class TipoCompensacaoInitializer {
	
	@Bean
	CommandLineRunner initTipoCompensacao(TipoCompensacaoRepository repository) {
		return args -> {
			List<String> tipos = Arrays.asList(
				"Acréscimo",
				"Abono"
			);
			
			tipos.forEach(tipoNome -> {
				if (!repository.existsByTipoCompensacaoName(tipoNome)) {
					repository.save(new TipoCompensacao(tipoNome));
				}
			});
		};
	}
}
