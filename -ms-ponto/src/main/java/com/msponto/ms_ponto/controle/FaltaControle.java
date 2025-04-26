package com.msponto.ms_ponto.controle;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msponto.ms_ponto.entidade.mysql.Falta;
import com.msponto.ms_ponto.repositorio.mysql.FaltaRepositorio;


@RestController
@RequestMapping("/faltas")
public class FaltaControle {

    @Autowired
    private FaltaRepositorio faltaRepositorio;

    @GetMapping("/")
    public ResponseEntity<List<Falta>> getAllFaltas() {
        List<Falta> faltas = faltaRepositorio.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(faltas);
    }

    @GetMapping("/{usuario_cod}")
    public ResponseEntity<List<Falta>> getAllFaltas(@PathVariable Long usuario_cod) {
        List<Falta> faltas = faltaRepositorio.findByUsuarioCod(usuario_cod);
        return ResponseEntity.status(HttpStatus.OK).body(faltas);
    }

    @GetMapping("/{usuario_cod}/dia")
    public ResponseEntity<Falta> getAllFaltas(@PathVariable Long usuario_cod, @RequestParam("data") String data) {
        LocalDate converted_data = LocalDate.parse(data);
        Falta falta = faltaRepositorio.findByFaltaDiaAndUsuarioCod(converted_data, usuario_cod);
        return ResponseEntity.status(HttpStatus.OK).body(falta);
    }
    
}
