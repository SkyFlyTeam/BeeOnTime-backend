package com.ms.banco_horas.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.banco_horas.dto.RelatorioDiarioDTO;
import com.ms.banco_horas.dto.RelatorioMensalDTO;
import com.ms.banco_horas.dto.RelatorioMensalFuncDTO;
import com.ms.banco_horas.service.RelatorioService;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController {

	@Autowired
	RelatorioService relatorio_service;

    @GetMapping("/mensal/{data}")
	private List<RelatorioMensalDTO> getRelatorioMensal(@PathVariable LocalDate data) {
		return relatorio_service.getRelatorioMensal(data);
	}

	@GetMapping("/diario/{data}/usuario/{usuarioCod}")
	private List<RelatorioDiarioDTO> getRelatorioDiarioUsuario(@PathVariable LocalDate data, @PathVariable Long usuarioCod) {
		return relatorio_service.getRelatorioDiarioUsuario(usuarioCod, data);
	}

	@GetMapping("/mensal/{data}/usuario/{usuarioCod}")
	private List<RelatorioMensalFuncDTO> getRelatorioMesesUsuario(@PathVariable LocalDate data, @PathVariable Long usuarioCod) {
		return relatorio_service.getRelatorioMesesUsuario(usuarioCod, data);
	}

}
