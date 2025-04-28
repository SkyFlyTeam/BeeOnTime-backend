package com.ms.banco_horas.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.ms.banco_horas.model.BancoHoras;
import com.ms.banco_horas.model.HistoricoCompensacao;
import com.ms.banco_horas.service.HistoricoCompensacaoService;

@RestController
@RequestMapping("/historico_compensacao")
public class HistoricoCompensacaoController {
	
	@Autowired
	private HistoricoCompensacaoService service;
	
	@GetMapping
	private List<HistoricoCompensacao> findAll() {
		return service.findAll();
	}
	
	@GetMapping("/usuario/{usuarioCod}")
	private List<HistoricoCompensacao> findByUsuario(@PathVariable long usuarioCod) {
		return service.findAllByUsuario(usuarioCod);
	}
	
	@PostMapping("/cadastrar")
	private ResponseEntity<?> save(@RequestBody HistoricoCompensacao historico) {
		if(historico.getBancoHorasCod() == null || historico.getHistCompensacaoTotal() == null || historico.getTipoCompensacaoCod() == null) {
		    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		service.save(historico);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deletar")
	private void delete(@RequestBody HistoricoCompensacao historico) {
		service.delete(historico);
	}
	
	@PutMapping("/editar")
	private ResponseEntity<HistoricoCompensacao> edit(@RequestBody HistoricoCompensacao historico) {
		HistoricoCompensacao updated = service.edit(historico);
		return ResponseEntity.ok(updated);
	}
}






