package com.msponto.ms_ponto.ms_clients;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


import com.msponto.ms_ponto.dto.FeriadoDTO;
import com.msponto.ms_ponto.dto.FolgaDTO;
import com.msponto.ms_ponto.dto.JornadaDTO;
import com.msponto.ms_ponto.dto.UsuarioDTO;

import reactor.core.publisher.Mono;


@Service
public class UsuarioClient {
    private final WebClient.Builder webUsuarioBuilder;

    public UsuarioClient(WebClient.Builder webUsuarioBuilder) {
        this.webUsuarioBuilder = webUsuarioBuilder;
    }

    public List<UsuarioDTO> getAllUsuarios() {
        // URL do microserviço de usuarios
        String usuarioServiceUrl = "http://msusuario:8081/usuario/usuarios"; 
        
        return webUsuarioBuilder.build()
                .get()
                .uri(usuarioServiceUrl)
                .retrieve()
                .bodyToFlux(UsuarioDTO.class) // Retorna um Flux (assíncrono)
                .collectList() // Coleta a lista de usuários
                .block(); // Bloqueia a execução até que os dados sejam retornados (só no caso de você precisar dos dados de forma síncrona)
    }

    public UsuarioDTO getUsuarioByCod(Long usuario_cod) {
        // URL do microserviço de usuarios
        String usuarioServiceUrl = "http://msusuario:8081/usuario/" + usuario_cod; 
        
        return webUsuarioBuilder.build()
            .get()
            .uri(usuarioServiceUrl)
            .retrieve()
            .bodyToMono(UsuarioDTO.class) // Retorna um Mono, que é o tipo certo para um único item
            .block(); // Bloqueia a execução até que os dados sejam retornados (isso é sincrono)
    }

    public Optional<FeriadoDTO> getFeriadoByEmpCodAndDate(Long empCod, LocalDate date) {
        String usuarioServiceUrl = "http://msusuario:8081/feriado/empresa" + empCod + "/data/" + date;

        FeriadoDTO feriado = null;
        try {
            feriado = webUsuarioBuilder.build()
                .get()
                .uri(usuarioServiceUrl)
                .retrieve()
                .onStatus(status -> status.value() == 404, response -> {
                    return Mono.empty();
                })
                .bodyToMono(FeriadoDTO.class)
                .block();
        } catch (Exception e) {
            return Optional.empty();
        }

        return Optional.ofNullable(feriado);
    }

    public JornadaDTO getJornadaByUsuario(Long usuario_cod) {
    	String usuarioServiceUrl = "http://msusuario:8081/jornada/usuario/" + usuario_cod;
    	
    	return webUsuarioBuilder.build()
    		.get()
    		.uri(usuarioServiceUrl)
    		.retrieve()
    		.bodyToMono(JornadaDTO.class)
    		.block();
    }

    public List<FolgaDTO> getFolgasByUsuario(Long usuarioCod) {
        String usuarioServiceUrl = "http://msusuario:8081/folgas/usuario/" + usuarioCod;
        
        return webUsuarioBuilder.build()
                .get()
                .uri(usuarioServiceUrl)
                .retrieve()
                .bodyToFlux(FolgaDTO.class) // Retorna um Flux (assíncrono)
                .collectList() // Coleta a lista de usuários
                .block(); // Bloqueia a execução até que os dados sejam retornados (só no caso de você precisar dos dados de forma síncrona)
    }
}
