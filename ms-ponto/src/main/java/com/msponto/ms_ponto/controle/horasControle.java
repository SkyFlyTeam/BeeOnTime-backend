package com.msponto.ms_ponto.controle;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
import com.msponto.ms_ponto.entidade.mysql.Horas;
import com.msponto.ms_ponto.servico.HorasServico;



@RestController
@RequestMapping("/horas")
public class horasControle {

    @Autowired
    private HorasServico horas_servico;

    // Retorna todas as horas de um usuário
    @GetMapping("/usuario/{usuario_cod}")
    public ResponseEntity<List<Horas>>  getUsuarioHoras(@PathVariable Long usuario_cod) {
        List<Horas> usuario_horas = horas_servico.getUsuarioHoras(usuario_cod);
        return ResponseEntity.status(HttpStatus.OK).body(usuario_horas);
    }

    // Retorna as horas de um usuário em determinado dia
    @PostMapping("/usuario/{usuario_cod}/dia")
    public ResponseEntity<List<Horas>>  getUsuarioHorasDate(@PathVariable Long usuario_cod, @RequestBody LocalDate data) {
        List<Horas> usuario_horas = horas_servico.getUsuarioHorasByDate(usuario_cod, data);
        return ResponseEntity.status(HttpStatus.OK).body(usuario_horas);
    }

    // Retorna as horas de um usuário em determinado periodo
    @PostMapping("/usuario/{usuario_cod}/periodo")
    public ResponseEntity<List<Horas>>  getUsuarioHorasByPeriod(@PathVariable Long usuario_cod, @RequestBody PeriodoDTO periodo) {
        LocalDate startDate = periodo.getStartDateAsDate();
        LocalDate endDate = periodo.getEndDateAsDate();
        List<Horas> usuario_horas = horas_servico.getUsuarioHorasByPeriod(usuario_cod, startDate, endDate);
        return ResponseEntity.status(HttpStatus.OK).body(usuario_horas);
    }

    // Cria um novo registro de horas 
    @PostMapping("/cadastrar")
    public ResponseEntity<String> createHoras(@RequestBody Horas horas) {
        Boolean horas_sucessful = horas_servico.createHoras(horas);
        if(!horas_sucessful){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao cadastrar horas.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Horas cadastrada com sucesso");
    }

    // Atualiza um registro de horas 
    @PutMapping("/atualizar")
    public ResponseEntity<String> updateHoras(@RequestBody Horas horas_att) {
        Boolean horas_sucessful = horas_servico.createHoras(horas_att);
        if(!horas_sucessful){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao atualizar horas.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Horas atualizadas com sucesso");
    }
}
