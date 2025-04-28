package com.msponto.ms_ponto.controle;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msponto.ms_ponto.dto.PeriodoDTO;
import com.msponto.ms_ponto.entidade.mongo.MarcacaoPontos;
import com.msponto.ms_ponto.servico.MarcacaoPontosServico;


@RestController
@RequestMapping("/mpontos")
public class MarcacaoPontosControle {

    @Autowired
    MarcacaoPontosServico mponto_servico;

    // Recuperar todos os pontos de um usuário pelo código
    @GetMapping("/usuario/{usuario_cod}")
    public List<MarcacaoPontos> getPontosUsuario(@PathVariable Long usuario_cod) {
        List<MarcacaoPontos> pontos = mponto_servico.getPontosUsuario(usuario_cod);
        return pontos;
    }

    // Recuperar todos os pontos dentro de um certo período
    @PostMapping("/usuario/{usuario_cod}/periodo")
    public List<MarcacaoPontos> getPontosUsuarioByPeriod(@PathVariable Long usuario_cod, @RequestBody PeriodoDTO periodo) {
        LocalDate startDate = periodo.getDataInicioAsDate();
        LocalDate endDate = periodo.getDataFimAsDate();
        List<MarcacaoPontos> pontos = mponto_servico.getPontosUsuarioByPeriod(usuario_cod, startDate, endDate);
        return pontos;
    }

    // Recupera a marcação de hora dado um horas código
    @GetMapping("/porHorasCod/{horas_cod}")
    public ResponseEntity<?> getPontosByHorasCod(@PathVariable Long horas_cod) {
        Optional<MarcacaoPontos> pontos = mponto_servico.getPontosUsuarioByCodHoras(horas_cod);
        if(!pontos.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao recuperar horas.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pontos.get());
    }

    // Realiza o cadastro de um novo ponto adicionando a uma marcaoção já existente ou criando nova
    @PostMapping("/baterPonto")
    public ResponseEntity<String> createPonto(@RequestBody MarcacaoPontos marcacaoPonto) {

        Boolean ponto_sucessful = mponto_servico.baterPonto(marcacaoPonto);

        if(!ponto_sucessful){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao bater o ponto.");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Ponto batido com sucesso!");
    }
    
    // Atualizar um ponto
    @PutMapping("/atualizar")
    public ResponseEntity<String> putAtualizarPontos(@RequestBody MarcacaoPontos mpontos_att) {
        Boolean att_sucessful = mponto_servico.updateMponto(mpontos_att);
    
        if (!att_sucessful) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao atualizar os pontos.");
        }
    
        return ResponseEntity.status(HttpStatus.OK).body("Ponto atualizado com sucesso!");
    }
}
