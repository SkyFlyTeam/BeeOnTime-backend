package com.ms.espelho_ponto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import com.ms.espelho_ponto.dto.HorasTrabalhadasDTO;
import java.util.List;

@Service
public class HorasTrabalhadasService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String URL_SERVICO_HORAS_TRABALHADAS = "http://msponto:8082/relatorio/mensal/";
    private static final String URL_SERVICO_HORAS = "http://msponto:8082/relatorio/diario/";

    public List<HorasTrabalhadasDTO> getHorasTrabalhadasByDate(String date){
        try {
            ResponseEntity<List<HorasTrabalhadasDTO>> response = restTemplate.exchange(
                URL_SERVICO_HORAS_TRABALHADAS + "/" + date, 
                HttpMethod.GET, 
                HttpEntity.EMPTY, 
                new ParameterizedTypeReference<List<HorasTrabalhadasDTO>>() {}
            );
            
            return response.getBody();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<HorasTrabalhadasDTO> getHorasDiariasByDateAndUserCod(String date , int usuarioCod){
        try {
            ResponseEntity<List<HorasTrabalhadasDTO>> response = restTemplate.exchange(
                URL_SERVICO_HORAS + "/" + date + "/usuario/" + usuarioCod, 
                HttpMethod.GET, 
                HttpEntity.EMPTY, 
                new ParameterizedTypeReference<List<HorasTrabalhadasDTO>>() {}
            );
            
            return response.getBody();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
