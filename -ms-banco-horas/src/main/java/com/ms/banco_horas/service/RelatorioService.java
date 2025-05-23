package com.ms.banco_horas.service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ms.banco_horas.dto.RelatorioDiarioDTO;
import com.ms.banco_horas.dto.RelatorioMensalDTO;
import com.ms.banco_horas.dto.RelatorioMensalFuncDTO;
import com.ms.banco_horas.dto.UsuarioDTO;
import com.ms.banco_horas.model.BancoHoras;
import com.ms.banco_horas.model.ExtrasPagas;
import com.ms.banco_horas.model.HistoricoCompensacao;

@Service
public class RelatorioService {
    
	@Autowired
	public ExtrasPagasService extraPaga_service;

	@Autowired
	public BancoHorasService bancoHoras_service;
	
	@Autowired
    private RestTemplate restTemplate;

    private static final String URL_SERVICO_USUARIO = "http://msusuario:8081/usuario"; 

	// Relatório mensal de cada usuário
    public List<RelatorioMensalDTO> getRelatorioMensal(LocalDate data) {
		try {
             ResponseEntity<List<UsuarioDTO>> response = restTemplate.exchange(
                URL_SERVICO_USUARIO + "/usuarios", 
                HttpMethod.GET, 
                HttpEntity.EMPTY, 
                new ParameterizedTypeReference<List<UsuarioDTO>>() {}
            );
            
            List<UsuarioDTO> usuarios = response.getBody();
            
			List<RelatorioMensalDTO> relatoriosMensais = new ArrayList<>();

			for (UsuarioDTO usuario : usuarios) {
				if(usuario.getNivelAcesso_cod() == 0){
					continue; 
				}
				RelatorioMensalDTO relatorioMensal = gerarRelatorioMensalUsuario(usuario, data);
				relatoriosMensais.add(relatorioMensal);
			}

			return relatoriosMensais;

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList(); 
        }
	}

	public RelatorioMensalDTO gerarRelatorioMensalUsuario(UsuarioDTO usuario, LocalDate data){
		ExtrasPagas extraPaga = extraPaga_service.findMostRecentByDateAndUsuarioCod(usuario.getUsuario_cod(), data);
    	Double saldo_extraPaga = (extraPaga != null) ? extraPaga.getExtrasPagasSaldoAtual() : 0.0; 

		// Buscar o saldo acumulado de banco de horas mais recente até a data fornecida
		BancoHoras bancoHoras = bancoHoras_service.findMostRecentByDateAndUsuarioCod(usuario.getUsuario_cod(), data);
		Double saldo_acumulado = (bancoHoras != null) ? bancoHoras.getBancoHorasSaldoAtual() : 0.0; 
	
		Double saldo_horasAbonadas = getHorasAbonadasPorUsuario(usuario.getUsuario_cod(), data);
		RelatorioMensalDTO relatorioMensal = new RelatorioMensalDTO();
		relatorioMensal.setUsuarioCod(usuario.getUsuario_cod());
		relatorioMensal.setExtrasPagas(saldo_extraPaga);
		relatorioMensal.setSaldoAcumulado(saldo_acumulado);
		relatorioMensal.setHorasAbonadas(saldo_horasAbonadas);
		
		return relatorioMensal;
	}
	

	private Double getHorasAbonadasPorUsuario(Long usuarioCod, LocalDate data) {
		List<BancoHoras> bancoHorasList = bancoHoras_service.findByPeriodAndUsuarioCod(usuarioCod, LocalDate.of(data.getYear(), data.getMonth(), 1), data);
		
		Double horasAbonadas = 0.0;

		for (BancoHoras bancoHoras : bancoHorasList) {
			List<HistoricoCompensacao> historicoCompensacoes = bancoHoras.getHistoricoCompensacoes();
			
			for (HistoricoCompensacao historico : historicoCompensacoes) {
				if (historico.getTipoCompensacaoCod().getTipoCompensacaoName().equalsIgnoreCase("Abono")) {	
					horasAbonadas += historico.getHistCompensacaoTotal();
				}
			}
		}

		return horasAbonadas;
	}

	// Relatório mensal de um usuário no último seis meses 
	public List<RelatorioMensalFuncDTO> getRelatorioMesesUsuario(Long usuarioCod, LocalDate data) {
		try {
			// Lista para armazenar os relatórios de cada mês
			List<RelatorioMensalFuncDTO> relatorios = new ArrayList<>();

			// Calculando os últimos seis meses
			List<LocalDate> meses = calcularUltimosSeisMeses(data);

			// Para cada mês, pegar os dados de banco de horas
			for (LocalDate mes : meses) {
				RelatorioMensalFuncDTO relatorio = gerarRelatorioMensalUsuarioFunc(usuarioCod, mes);
				relatorios.add(relatorio);
			}

			return relatorios;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	public RelatorioMensalFuncDTO gerarRelatorioMensalUsuarioFunc(Long usuarioCod, LocalDate data){
		ExtrasPagas extraPaga = extraPaga_service.findMostRecentByDateAndUsuarioCod(usuarioCod, data);
    	Double saldo_extraPaga = (extraPaga != null) ? extraPaga.getExtrasPagasSaldoAtual() : 0.0; 

		// Buscar o saldo acumulado de banco de horas mais recente até a data fornecida
		BancoHoras bancoHoras = bancoHoras_service.findMostRecentByDateAndUsuarioCod(usuarioCod, data);
		Double saldo_acumulado = (bancoHoras != null) ? bancoHoras.getBancoHorasSaldoAtual() : 0.0; 
	
		Double saldo_horasAbonadas = getHorasAbonadasPorUsuario(usuarioCod, data);
		RelatorioMensalFuncDTO relatorioMensal = new RelatorioMensalFuncDTO();
		relatorioMensal.setUsuarioCod(usuarioCod);
		relatorioMensal.setData(data);
		relatorioMensal.setExtrasPagas(saldo_extraPaga);
		relatorioMensal.setSaldoAcumulado(saldo_acumulado);
		relatorioMensal.setHorasAbonadas(saldo_horasAbonadas);
		
		return relatorioMensal;
	}

	private List<LocalDate> calcularUltimosSeisMeses(LocalDate data) {
		List<LocalDate> meses = new ArrayList<>();
	
		// Adiciona o mês atual
		meses.add(data.withDayOfMonth(data.lengthOfMonth()));  // Último dia do mês atual
	
		// Adiciona os seis meses anteriores
		for (int i = 1; i <= 5; i++) {
			LocalDate mesAnterior = data.minusMonths(i).withDayOfMonth(data.minusMonths(i).lengthOfMonth());  // Último dia do mês anterior
			meses.add(mesAnterior);
		}
	
		return meses;
	}

	// Relatório diário de um usuário
	public List<RelatorioDiarioDTO> getRelatorioDiarioUsuario(Long usuarioCod, LocalDate data) {
		String usuarioUrl = URL_SERVICO_USUARIO + "/" + usuarioCod;
		try {
			UsuarioDTO usuario = restTemplate.getForObject(usuarioUrl, UsuarioDTO.class);

			List<LocalDate> datasDoMes = gerarDatasDoMes(data);

			List<RelatorioDiarioDTO> relatoriosDiarios = new ArrayList<>();
			for (LocalDate dia : datasDoMes) {
				RelatorioDiarioDTO relatorioDiario = gerarRelatorioDiarioUsuario(usuario, dia);
				relatoriosDiarios.add(relatorioDiario);
			}

			return relatoriosDiarios;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	private List<LocalDate> gerarDatasDoMes(LocalDate data) {
		List<LocalDate> datasDoMes = new ArrayList<>();
		
		// Determina o primeiro e o último dia do mês com base na data fornecida
		LocalDate primeiroDiaDoMes = data.withDayOfMonth(1);
		LocalDate ultimoDiaDoMes = data.with(TemporalAdjusters.lastDayOfMonth());
		
		// Gera as datas de todos os dias do mês
		for (LocalDate dia = primeiroDiaDoMes; !dia.isAfter(ultimoDiaDoMes); dia = dia.plusDays(1)) {
			datasDoMes.add(dia);
		}
		
		return datasDoMes;
	}

	public RelatorioDiarioDTO gerarRelatorioDiarioUsuario(UsuarioDTO usuario, LocalDate data){
		ExtrasPagas extraPaga = extraPaga_service.findByDateAndUsuarioCod(usuario.getUsuario_cod(), data);
    	Double saldo_extraPaga = (extraPaga != null) ? extraPaga.getExtrasPagasSaldoAtual() : 0.0; 

		BancoHoras bancoHoras = bancoHoras_service.findByDateAndUsuarioCod(data, usuario.getUsuario_cod());
		Double saldo_acumulado = (bancoHoras != null) ? bancoHoras.getBancoHorasSaldoAtual() : 0.0; 
	
		Double saldo_horasAbonadas = getHorasAbonadasDiariaPorUsuario(bancoHoras);
		RelatorioDiarioDTO relatorioDiario = new RelatorioDiarioDTO();
		relatorioDiario.setUsuarioCod(usuario.getUsuario_cod());
		relatorioDiario.setData(data);
		relatorioDiario.setExtrasPagas(saldo_extraPaga);
		relatorioDiario.setSaldoAcumulado(saldo_acumulado);
		relatorioDiario.setHorasAbonadas(saldo_horasAbonadas);
		
		return relatorioDiario;
	}

	private Double getHorasAbonadasDiariaPorUsuario(BancoHoras bancoHoras) {
		
		if (bancoHoras == null) {
			return 0.0; 
		}

		Double horasAbonadas = 0.0;

		List<HistoricoCompensacao> historicoCompensacoes = bancoHoras.getHistoricoCompensacoes();
		
		for (HistoricoCompensacao historico : historicoCompensacoes) {
			if (historico.getTipoCompensacaoCod().getTipoCompensacaoName().equalsIgnoreCase("Abono")) {	
				horasAbonadas += historico.getHistCompensacaoTotal();
			}
		}
		
		return horasAbonadas;
	}


}
