package com.ms.espelho_ponto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import com.ms.espelho_ponto.dto.EmpresaDTO;

@Service
public class EmpresaService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String URL_SERVICO_USUARIO = "http://msusuario:8081/empresa"; 

    public EmpresaDTO getEmpresaById(long id){
        try {
            ResponseEntity<EmpresaDTO> response = restTemplate.exchange(
                URL_SERVICO_USUARIO + "/" + id, 
                HttpMethod.GET, 
                HttpEntity.EMPTY, 
                new ParameterizedTypeReference<EmpresaDTO>() {}
            );
            
            EmpresaDTO empresas = response.getBody();
            return empresas;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
