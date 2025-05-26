package com.ms.espelho_ponto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ms.espelho_ponto.dto.UsuarioDTO;

@Service
public class UsuarioService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String URL_SERVICO_USUARIO = "http://msusuario:8081/usuario"; 

    public UsuarioDTO getUsuarioById(long id){
        try {
            ResponseEntity<UsuarioDTO> response = restTemplate.exchange(
                URL_SERVICO_USUARIO + "/" + id, 
                HttpMethod.GET, 
                HttpEntity.EMPTY, 
                new ParameterizedTypeReference<UsuarioDTO>() {}
            );
            
            UsuarioDTO usuarios = response.getBody();
            return usuarios;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<UsuarioDTO> getAllUsuario(){
        try {
            ResponseEntity<List<UsuarioDTO>> response = restTemplate.exchange(
                URL_SERVICO_USUARIO + "/" + "usuarios", 
                HttpMethod.GET, 
                HttpEntity.EMPTY, 
                new ParameterizedTypeReference<List<UsuarioDTO>>() {}
            );
            
            List<UsuarioDTO> usuarios = response.getBody();
            return usuarios;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
