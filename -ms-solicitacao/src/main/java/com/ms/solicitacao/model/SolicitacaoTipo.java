package com.ms.solicitacao.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "solicitacao_tipo")
@NoArgsConstructor
@AllArgsConstructor
public class SolicitacaoTipo {

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