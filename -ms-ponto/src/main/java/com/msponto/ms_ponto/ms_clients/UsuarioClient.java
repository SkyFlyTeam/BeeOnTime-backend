package com.msponto.ms_ponto.ms_clients;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.msponto.ms_ponto.dto.UsuarioDTO;

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
}
