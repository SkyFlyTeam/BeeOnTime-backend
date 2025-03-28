package com.ms.solicitacao.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.solicitacao.model.Solicitacao;
import com.ms.solicitacao.model.SolicitacaoTipo;
import com.ms.solicitacao.repository.SolicitacaoTipoRepository;
import com.ms.solicitacao.service.SolicitacaoService;

@RestController
@RequestMapping("/solicitacao")
public class SolicitacaoController {

	@Autowired
	private SolicitacaoService solicitacaoService;
	
	@Autowired
    private ObjectMapper objectMapper;
	
	@GetMapping
	private List<Solicitacao> findAll() {
		return solicitacaoService.findAll();
	}
	
	@GetMapping("/{id}")
	private Solicitacao findById(@PathVariable long id) {
		return solicitacaoService.findById(id);
	}
	
	@PostMapping(value = "/cadastrar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Solicitacao> save(
	    @RequestParam("solicitacaoJson") String solicitacaoJson,
	    @RequestParam(value = "solicitacaoAnexo", required = false) MultipartFile solicitacaoAnexo
	) {
	    try {
	        Solicitacao solicitacao = objectMapper.readValue(solicitacaoJson, Solicitacao.class);

	        if (solicitacaoAnexo != null && !solicitacaoAnexo.isEmpty()) {
	            byte[] bytes = solicitacaoAnexo.getBytes();
	            solicitacao.setSolicitacaoAnexo(bytes);
	            solicitacao.setSolicitacaoAnexoNome(solicitacaoAnexo.getOriginalFilename());
	        } else {
	            System.out.println("⚠️ Nenhum anexo recebido.");
	        }

	        Solicitacao saved = solicitacaoService.save(solicitacao);
	        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	    }
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
