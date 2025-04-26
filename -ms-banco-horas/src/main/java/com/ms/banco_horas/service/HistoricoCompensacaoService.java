package com.ms.banco_horas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ms.banco_horas.model.BancoHoras;
import com.ms.banco_horas.model.HistoricoCompensacao;
import com.ms.banco_horas.repository.BancoHorasRepository;
import com.ms.banco_horas.repository.HistoricoCompensacaoRepository;

@Service
public class HistoricoCompensacaoService {
	
	@Autowired
	public HistoricoCompensacaoRepository repository;
	
	@Autowired
	public BancoHorasService bancoHorasService;
	
	@Autowired
	public BancoHorasRepository bancoHorasRepository;
	
	public List<HistoricoCompensacao> findAll() {
		return repository.findAll();
	}
	
	public List<HistoricoCompensacao> findAllByUsuario(long usuarioCod) {
        return repository.findByBancoHorasCod_UsuarioCod(usuarioCod);
    }
	
	public HistoricoCompensacao findById(long histCompensacaoCod) {
		return repository.findById(histCompensacaoCod).orElseThrow(() -> new RuntimeException("Histórico de compensação não encontrado"));
	}
	
	public HistoricoCompensacao save(HistoricoCompensacao historico) {
		BancoHoras bancoHoras = bancoHorasService.findById(historico.getBancoHorasCod().getBancoHorasCod());
		if (historico.getTipoCompensacaoCod().getTipoCompensacaoCod() == 1) {
			Double horas = bancoHoras.getBancoHorasSaldoAtual() + historico.getHistCompensacaoTotal();
			bancoHoras.setBancoHorasSaldoAtual(horas);
			bancoHorasRepository.save(bancoHoras);
		} else {
			Double horas = bancoHoras.getBancoHorasSaldoAtual() - historico.getHistCompensacaoTotal();
			bancoHoras.setBancoHorasSaldoAtual(horas);
			bancoHorasRepository.save(bancoHoras);
		}
		return repository.save(historico);
	}
	
	public HistoricoCompensacao edit(HistoricoCompensacao historico) {
		HistoricoCompensacao selecionado = repository.findById(historico.getHistCompensacaoCod())
				.orElseThrow(() -> new RuntimeException("Histórico não encontrado!"));
		
		if(historico.getHistCompensacaoTotal() != null) {
			selecionado.setHistCompensacaoTotal(historico.getHistCompensacaoTotal());
		}
		
		if(historico.getTipoCompensacaoCod() != null) {
			selecionado.setTipoCompensacaoCod(historico.getTipoCompensacaoCod());
		}
		
		if(historico.getBancoHorasCod() != null) {
			selecionado.setBancoHorasCod(historico.getBancoHorasCod());
		}
		
		return repository.save(selecionado);
	}
	
	public void delete(HistoricoCompensacao historico) {
		repository.deleteById(historico.getHistCompensacaoCod());
	}
}
















