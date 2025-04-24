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
import com.ms.banco_horas.service.BancoHorasService;

@RestController
@RequestMapping("/banco_horas")
public class BancoHorasController {
	
	@Autowired
	private BancoHorasService service;
	
	
	@GetMapping
	private List<BancoHoras> findAll() {
		return service.findAll();
	}
	
	@GetMapping("/{bancoHorasCod}")
	private BancoHoras findById(@PathVariable long bancoHorasCod) {
		return service.findById(bancoHorasCod);
	}
	
	@GetMapping("/usuario/{usuarioCod}")
	private ResponseEntity<?> findAllByUsuario(@PathVariable long usuarioCod) {
		List<BancoHoras> bancoHoras = service.findAllByUsuario(usuarioCod);
		if (bancoHoras != null && !bancoHoras.isEmpty()) {
			return new ResponseEntity<>(bancoHoras, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
	}

	@GetMapping("/usuario/{usuarioCod}/horas-disponiveis")
	private ResponseEntity<Integer> findHorasDisponiveisByUsuario(@PathVariable long usuarioCod) {
		Integer horasDisponiveis = service.findHorasDisponiveisByUsuario(usuarioCod);
		if (horasDisponiveis != null) {
			return new ResponseEntity<>(horasDisponiveis, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


	
	@PostMapping("/cadastrar")
	private ResponseEntity<?> save(@RequestBody BancoHoras bancoHoras) {
	    if (bancoHoras.getBancoHorasSaldoAtual() == null || bancoHoras.getUsuarioCod() == null) {
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }

	    BancoHoras savedBancoHoras = service.save(bancoHoras);
	    if (savedBancoHoras != null) {
	        return new ResponseEntity<>(HttpStatus.CREATED); 
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
	    }
	}

	
	@DeleteMapping("/deletar")
	private void delete(@RequestBody BancoHoras bancoHoras) {
		service.delete(bancoHoras);
	}
	
	@PutMapping("/editar")
	private ResponseEntity<BancoHoras> edit(@RequestBody BancoHoras bancoHoras) {
		BancoHoras updated = service.edit(bancoHoras);
		return ResponseEntity.ok(updated);
	}
}







