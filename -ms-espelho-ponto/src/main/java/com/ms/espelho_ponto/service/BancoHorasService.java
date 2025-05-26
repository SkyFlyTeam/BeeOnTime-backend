package com.ms.espelho_ponto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ms.espelho_ponto.dto.BancoHorasDTO;

@Service
public class BancoHorasService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String URL_SERVICO_USUARIO = "http://msbancohoras:8084/relatorio/mensal/"; 

    public List<BancoHorasDTO> getBancoHoras(String data){
        try {
            ResponseEntity<List<BancoHorasDTO>> response = restTemplate.exchange(
                URL_SERVICO_USUARIO + "/" + data, 
                HttpMethod.GET, 
                HttpEntity.EMPTY, 
                new ParameterizedTypeReference<List<BancoHorasDTO>>() {}
            );
            
            List<BancoHorasDTO> bancoHoras = response.getBody();
            return bancoHoras;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
