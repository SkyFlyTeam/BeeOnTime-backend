package com.msponto.ms_ponto.controle;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msponto.ms_ponto.dto.RelatorioHorasDTO;
import com.msponto.ms_ponto.dto.RelatorioHorasMesDTO;
import com.msponto.ms_ponto.servico.RelatorioServico;

@RestController
@RequestMapping("/relatorio")
public class RelatorioControle {
    @Autowired
    RelatorioServico relatorio_servico;

    @GetMapping("/mensal/{data}")
	private List<RelatorioHorasDTO> getRelatorioMensal(@PathVariable LocalDate data) {
		return relatorio_servico.getHorasRelatorioMensal(data);
	}

    @GetMapping("/diario/{data}/usuario/{usuarioCod}")
	private List<RelatorioHorasMesDTO> getHorasRelatorioDiarioUsuario(@PathVariable LocalDate data, @PathVariable Long usuarioCod) {
		return relatorio_servico.getHorasRelatorioDiarioUsuario(usuarioCod, data);
	}

    @GetMapping("/mensal/{data}/usuario/{usuarioCod}")
	private List<RelatorioHorasMesDTO> getHorasRelatorio6MesesUsuario(@PathVariable LocalDate data, @PathVariable Long usuarioCod) {
		return relatorio_servico.getHorasRelatorio6MesesUsuario(usuarioCod, data);
	}
}
