package com.ms.solicitacao.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.solicitacao.model.SolicitacaoTipo;
import com.ms.solicitacao.repository.SolicitacaoTipoRepository;

@Service
public class SolicitacaoTipoService {

	@Autowired
	private SolicitacaoTipoRepository solicitacaoTipoRepository;
	
	public List<SolicitacaoTipo> findAll(){
		return solicitacaoTipoRepository.findAll();
	}
	
	public SolicitacaoTipo findById(long id) {
		SolicitacaoTipo tipoSelecionado = solicitacaoTipoRepository.findById(id).get();
		return tipoSelecionado;
	}
	
	public SolicitacaoTipo save(SolicitacaoTipo solicitacaoTipo) {
		return solicitacaoTipoRepository.save(solicitacaoTipo);
	}
	
	public void delete(SolicitacaoTipo solicitacaoTipo) {
		solicitacaoTipoRepository.deleteById(solicitacaoTipo.getTipoSolicitacaoCod());
	}
}
