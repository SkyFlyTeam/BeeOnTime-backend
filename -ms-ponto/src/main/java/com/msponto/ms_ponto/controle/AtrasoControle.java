package com.msponto.ms_ponto.controle;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msponto.ms_ponto.entidade.mysql.Atraso;
import com.msponto.ms_ponto.servico.AtrasoServico;

@RestController
@RequestMapping("/atraso")
public class AtrasoControle {
	
	@Autowired
	AtrasoServico atrasoservico;
	
	@GetMapping("/atrasos")
	private ResponseEntity<List<Atraso>> getAtrasos(
	    @RequestParam(required = false) LocalDate dataInicio,
	    @RequestParam(required = false) LocalDate dataFim
	) {
	    List<Atraso> atrasos;

	    if (dataInicio == null && dataFim == null) {
	        atrasos = atrasoservico.findAll();
	    } else {
	        atrasos = atrasoservico.findByDate(dataInicio, dataFim);
	    }

	    return ResponseEntity.status(HttpStatus.OK).body(atrasos);
	}
	
	@GetMapping("/atrasos/{usuarioCod}")
	private ResponseEntity<List<Atraso>> getAtrasosByUsuario(@PathVariable Long usuarioCod){
		List<Atraso> atrasos = atrasoservico.findByUsuario(usuarioCod);
		return ResponseEntity.status(HttpStatus.OK).body(atrasos);
	}
	
	@GetMapping("/atrasos/setor/{setorCod}")
	private ResponseEntity<List<Atraso>> getAtrasosBySetor(@PathVariable Long setorCod){
		List<Atraso> atrasos = atrasoservico.findBySetor(setorCod);
		return ResponseEntity.status(HttpStatus.OK).body(atrasos);
	}
	
	@PostMapping("/cadastrar")
	private ResponseEntity<?> save(@RequestBody Atraso atraso) {
		if (atraso.getAtrasoTempo() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Atraso savedAtraso = atrasoservico.save(atraso);
		if(savedAtraso != null) {
			return new ResponseEntity<>(savedAtraso, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		}
	}
	
	@PutMapping("/editar")
	private ResponseEntity<?> edit(@RequestBody Atraso atraso) {
		if (atraso.getAtrasoCod() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		atrasoservico.edit(atraso);
		return ResponseEntity.ok("Atraso editado com sucesso.");
	}
	
	@DeleteMapping("/excluir")
	private ResponseEntity<?> delete(@RequestBody Atraso atraso) {
		if (atraso.getAtrasoCod() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		atrasoservico.delete(atraso);
		return ResponseEntity.ok("Atraso excluído com sucesso.");
	}
}
