package com.msponto.ms_ponto.controle;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msponto.ms_ponto.entidade.mongo.MarcacaoPontos;
import com.msponto.ms_ponto.servico.MarcacaoPontosServico;



@RestController
@RequestMapping("/mpontos")
public class MarcacaoPontosControle {
    @Autowired
    MarcacaoPontosServico mponto_servico;

    @PostMapping("/baterPonto")
    public ResponseEntity<String> createPonto(@RequestBody MarcacaoPontos marcacaoPonto) {
        
        // Adicionar lógica com o microsserviço para buscar o usuário e verificar se existe mesmo

        Boolean ponto_sucessful = mponto_servico.baterPonto(marcacaoPonto);

        if(!ponto_sucessful){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao bater o ponto.");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Ponto batido com sucesso!");
    }

    @GetMapping("/usuario/{usuario_cod}")
    public List<MarcacaoPontos> getPontosUsuario(@PathVariable Long usuario_cod) {
        List<MarcacaoPontos> pontos = mponto_servico.getPontosUsuario(usuario_cod);
        return pontos;
    }
}
