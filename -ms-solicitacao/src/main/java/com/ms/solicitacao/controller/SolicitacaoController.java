package com.ms.solicitacao.controller;

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

import com.ms.solicitacao.model.Solicitacao;
import com.ms.solicitacao.service.SolicitacaoService;

@RestController
@RequestMapping("/solicitacao")
public class SolicitacaoController {

	@Autowired
	private SolicitacaoService solicitacaoService;
	
	@GetMapping
	private List<Solicitacao> findAll() {
		return solicitacaoService.findAll();
	}
	
	@GetMapping("/{id}")
	private Solicitacao findById(@PathVariable long id) {
		return solicitacaoService.findById(id);
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Solicitacao> save(@RequestBody Solicitacao solicitacao) {
	    Solicitacao saved = solicitacaoService.save(solicitacao);
	    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	@DeleteMapping("/deletar")
	private void delete(@RequestBody Solicitacao solicitacao) {
		solicitacaoService.delete(solicitacao);
	}
	
	@PutMapping("/editar")
	public ResponseEntity<Solicitacao> edit(@RequestBody Solicitacao solicitacao) {
	    Solicitacao updated = solicitacaoService.edit(solicitacao);
	    return ResponseEntity.ok(updated);
	}

}
