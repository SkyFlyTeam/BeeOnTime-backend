package com.ms.banco_horas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TipoCompensacao {
	
	public TipoCompensacao() {
    }
	
	public TipoCompensacao(String tipoCompensacaoName) {
        this.tipoCompensacaoName = tipoCompensacaoName;
    }
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tipoCompensacao_cod")
	private long tipoCompensacaoCod;
	
	@Column(name = "tipoCompensacao_nome")
	private String tipoCompensacaoName;

	public long getTipoCompensacaoCod() {
		return tipoCompensacaoCod;
	}

	public void setTipoCompensacaoCod(long tipoCompensacaoCod) {
		this.tipoCompensacaoCod = tipoCompensacaoCod;
	}

	public String getTipoCompensacaoName() {
		return tipoCompensacaoName;
	}

	public void setTipoCompensacaoName(String tipoCompensacaoName) {
		this.tipoCompensacaoName = tipoCompensacaoName;
	}
	
	
}
