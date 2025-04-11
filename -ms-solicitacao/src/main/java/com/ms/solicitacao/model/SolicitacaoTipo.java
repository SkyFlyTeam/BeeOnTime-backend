package com.ms.solicitacao.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "solicitacao_tipo")
public class SolicitacaoTipo {
	public SolicitacaoTipo() {
		
	}

	public SolicitacaoTipo(String tipoSolicitacaoNome){
		this.tipoSolicitacaoNome = tipoSolicitacaoNome;
	}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tipoSolicitacaoCod;

    @Column(length = 50, nullable = false)
    private String tipoSolicitacaoNome;

	public long getTipoSolicitacaoCod() {
		return tipoSolicitacaoCod;
	}

	public void setTipoSolicitacaoCod(long tipoSolicitacaoCod) {
		this.tipoSolicitacaoCod = tipoSolicitacaoCod;
	}

	public String getTipoSolicitacaoNome() {
		return tipoSolicitacaoNome;
	}

	public void setTipoSolicitacaoNome(String tipoSolicitacaoNome) {
		this.tipoSolicitacaoNome = tipoSolicitacaoNome;
	}
    
    
}