package com.ms.solicitacao.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.solicitacao.model.Solicitacao;
import com.ms.solicitacao.model.SolicitacaoTipo;
import com.ms.solicitacao.repository.SolicitacaoRepository;
import com.ms.solicitacao.repository.SolicitacaoTipoRepository;

@Service
public class SolicitacaoService {
	
	@Autowired
	private SolicitacaoRepository solicitacaoRepository;
	
	@Autowired
	private SolicitacaoTipoRepository solicitacaoTipoRepository;
	
	public List<Solicitacao> findAll() {
		return solicitacaoRepository.findAll();
	}
	
	public Solicitacao findById(long id) {
		return solicitacaoRepository.findById(id).get();
	}
	
	public Solicitacao save(Solicitacao solicitacao) {
	    if (solicitacao.getTipoSolicitacaoCod() != null) {
	        Optional<SolicitacaoTipo> tipo = solicitacaoTipoRepository.findById(solicitacao.getTipoSolicitacaoCod().getTipoSolicitacaoCod());
	        tipo.ifPresent(tipoSolicitacao -> solicitacao.setTipoSolicitacaoCod(tipoSolicitacao));
	    }
	    return solicitacaoRepository.save(solicitacao);
	}
	
	public Solicitacao edit(Solicitacao solicitacao) {
	    Solicitacao selecionado = solicitacaoRepository.findById(solicitacao.getSolicitacaoCod())
	        .orElseThrow(() -> new RuntimeException("Solicitação não encontrada!"));

	    if (solicitacao.getSolicitacaoDevolutiva() != null) {
	        selecionado.setSolicitacaoDevolutiva(solicitacao.getSolicitacaoDevolutiva());
	    }
	    if (solicitacao.getSolicitacaoStatus() != null) {
	        selecionado.setSolicitacaoStatus(solicitacao.getSolicitacaoStatus());
	    }
	    if (solicitacao.getSolicitacaoAnexo() != null) {
	        selecionado.setSolicitacaoAnexo(solicitacao.getSolicitacaoAnexo());
	    }
	    if (solicitacao.getSolicitacaoMensagem() != null) {
	    	selecionado.setSolicitacaoMensagem(solicitacao.getSolicitacaoMensagem());
	    }

	    return solicitacaoRepository.save(selecionado);
	}


	public void delete(Solicitacao solicitacao) {
		solicitacaoRepository.deleteById(solicitacao.getSolicitacaoCod());
	}
}
