package com.msponto.ms_ponto.servico;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msponto.ms_ponto.dto.RelatorioHorasDTO;
import com.msponto.ms_ponto.dto.RelatorioHorasMesDTO;
import com.msponto.ms_ponto.dto.UsuarioDTO;
import com.msponto.ms_ponto.entidade.mysql.Horas;
import com.msponto.ms_ponto.ms_clients.UsuarioClient;

@Service
public class RelatorioServico {

    @Autowired
    HorasServico horas_servico;

    @Autowired
    private UsuarioClient usuarioClient;

    // Relatorio mensal de horas de todos os usuários
    public List<RelatorioHorasDTO> getHorasRelatorioMensal(LocalDate data){
        List<UsuarioDTO> usuarios = usuarioClient.getAllUsuarios();
        
        List<RelatorioHorasDTO> relatoriosMensais = new ArrayList<>();

        for (UsuarioDTO usuario : usuarios) {
            Float[] horasRelatorio = getHorasTotalAndDescontoByUsuario(usuario.getUsuario_cod(), data);
            RelatorioHorasDTO relatorioMensal = new RelatorioHorasDTO();
            relatorioMensal.setUsuarioCod(usuario.getUsuario_cod());
            relatorioMensal.setHorasTotal(horasRelatorio[0]);
            relatorioMensal.setDesconto(horasRelatorio[1]);
            relatoriosMensais.add(relatorioMensal);
        }

        return relatoriosMensais;
    }

    private Float[] getHorasTotalAndDescontoByUsuario(Long usuarioCod, LocalDate data) {
		List<Horas> horasList = horas_servico.getUsuarioHorasByPeriod(usuarioCod, LocalDate.of(data.getYear(), data.getMonth(), 1), data);
		
		Float totalHoras = 0.0f;
        Float desconto = 0.0f;

		for (Horas horas : horasList) {
			totalHoras += horas.getHorasTrabalhadas();
            desconto += horas.getHorasFaltantes();
		}

		return new Float[]{totalHoras, desconto};
	}

    // Relatório diário de um usuário no período de um mes
    public List<RelatorioHorasMesDTO> getHorasRelatorioDiarioUsuario(Long usuarioCod, LocalDate data){
        List<LocalDate> datasDoMes = gerarDatasDoMes(data);

        List<RelatorioHorasMesDTO> relatoriosDiarios = new ArrayList<>();
        for (LocalDate dia : datasDoMes) {
            Horas horasRelatorio = horas_servico.getUsuarioHorasByDate(usuarioCod, dia);



            RelatorioHorasMesDTO relatorioDiario = new RelatorioHorasMesDTO();
            relatorioDiario.setUsuarioCod(usuarioCod);
            relatorioDiario.setData(dia);

            if(horasRelatorio == null){
                relatorioDiario.setHorasTotal(0.0f);
                relatorioDiario.setDesconto(0.0f);
            }else{
                relatorioDiario.setHorasTotal(horasRelatorio.getHorasTrabalhadas());
                relatorioDiario.setDesconto(horasRelatorio.getHorasFaltantes());
            }

            relatoriosDiarios.add(relatorioDiario);
        }

        return relatoriosDiarios;
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

    // Relatório de horas no período de 6 meses de um usuário
    public List<RelatorioHorasMesDTO> getHorasRelatorio6MesesUsuario(Long usuarioCod, LocalDate data){
        List<LocalDate> dataMeses = calcularUltimosSeisMeses(data);

        List<RelatorioHorasMesDTO> relatoriosDiarios = new ArrayList<>();
        for (LocalDate dia : dataMeses) {
            Float[] horasRelatorio = getHorasTotalAndDescontoByUsuario(usuarioCod, dia);

            RelatorioHorasMesDTO relatorioDiario = new RelatorioHorasMesDTO();
            relatorioDiario.setUsuarioCod(usuarioCod);
            relatorioDiario.setData(dia);
            relatorioDiario.setHorasTotal(horasRelatorio[0]);
            relatorioDiario.setDesconto(horasRelatorio[1]);
            relatoriosDiarios.add(relatorioDiario);
        }

        return relatoriosDiarios;
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
}
