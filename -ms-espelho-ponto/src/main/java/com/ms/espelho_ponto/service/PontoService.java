package com.ms.espelho_ponto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ms.espelho_ponto.dto.PontoDTO;

@Service
public class PontoService {

    @Autowired
    private RestTemplate restTemplate;

    // URL of the external Ponto microservice
    private static final String URL_SERVICO_PONTO = "http://msponto:8082/mpontos/usuario/"; // Update with the correct service URL

    // Method to fetch PontoDTO by ID from another microservice
    public List<PontoDTO> getPontoByUsuarioId(int id) {
        try {
            ResponseEntity<List<PontoDTO>> response = restTemplate.exchange(
                URL_SERVICO_PONTO + "/" + id,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<List<PontoDTO>>() {}
            );

            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
