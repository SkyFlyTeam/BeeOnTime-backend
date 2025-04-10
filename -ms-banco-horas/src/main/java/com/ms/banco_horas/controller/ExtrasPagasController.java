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
import com.ms.banco_horas.model.ExtrasPagas;
import com.ms.banco_horas.service.ExtrasPagasService;

@RestController
@RequestMapping("/extras_pagas")
public class ExtrasPagasController {
	
	@Autowired
	private ExtrasPagasService service;
	
	@GetMapping
	private ResponseEntity<?> findAll() {
	    List<ExtrasPagas> extras = service.findAll();
	    if (extras == null || extras.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    
	    return new ResponseEntity<>(extras, HttpStatus.OK);
	}
	
	@GetMapping("/usuario/{usuarioCod}")
	private ResponseEntity<?> findByUsuario(@PathVariable long usuarioCod){
		 List<ExtrasPagas> extras = service.findAllByUsuario(usuarioCod);
		 return new ResponseEntity<>(extras, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<?> findById(@PathVariable long id){
		ExtrasPagas extra = service.findById(id);
		if(extra == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(extra, HttpStatus.OK);
	}
	
	@PostMapping("/cadastrar")
	private ResponseEntity<?> save(@RequestBody ExtrasPagas extrasPagas){
		if (extrasPagas.getExtrasPagasSaldoAtual() == null || extrasPagas.getUsuarioCod() == null) {
			return new ResponseEntity<ExtrasPagas>(HttpStatus.BAD_REQUEST);
		}
		
		service.save(extrasPagas);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deletar")
	private ResponseEntity<?> delete(@RequestBody ExtrasPagas extraPaga){
		service.delete(extraPaga);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/editar")
	private ResponseEntity<ExtrasPagas> edit(@RequestBody ExtrasPagas extraPaga) {
		ExtrasPagas updated = service.edit(extraPaga);
		return ResponseEntity.ok(updated);
	}
}












