package com.ms.solicitacao.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.solicitacao.model.SolicitacaoTipo;
import com.ms.solicitacao.service.SolicitacaoTipoService;

@RestController
@RequestMapping("/solicitacao-tipo")
public class SolicitacaoTipoController {
	
	@Autowired
	private SolicitacaoTipoService solicitacaoTipoService;
	
	@GetMapping
	public List<SolicitacaoTipo> findAll() {
		return solicitacaoTipoService.findAll();
	}
	
	@GetMapping("/{id}")
	public SolicitacaoTipo findById(@PathVariable long id) {
		return solicitacaoTipoService.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<SolicitacaoTipo> save(@RequestBody SolicitacaoTipo solicitacaoTipo) {
		SolicitacaoTipo saved = solicitacaoTipoService.save(solicitacaoTipo);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}
	
	@DeleteMapping("/deletar")
	public void delete(@RequestBody SolicitacaoTipo solicitacaoTipo) {
		solicitacaoTipoService.delete(solicitacaoTipo);
	}
}
