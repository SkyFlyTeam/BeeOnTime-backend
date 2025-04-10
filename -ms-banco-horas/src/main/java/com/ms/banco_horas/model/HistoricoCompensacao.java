package com.ms.banco_horas.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class,
  property = "histCompensacaoCod"
)
@Entity
public class HistoricoCompensacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "histCompensacao_cod")
	private long histCompensacaoCod;
	
	@Column(name = "histCompensacao_total")
	private Double histCompensacaoTotal;
	
	@ManyToOne
	@JoinColumn(name = "tipoCompensacaoCod", nullable = false)
	private TipoCompensacao tipoCompensacaoCod;
	
	@ManyToOne
    @JoinColumn(name = "bancoHorasCod", nullable = false) 
	private BancoHoras bancoHorasCod;

	public long getHistCompensacaoCod() {
		return histCompensacaoCod;
	}

	public void setHistCompensacaoCod(long histCompensacaoCod) {
		this.histCompensacaoCod = histCompensacaoCod;
	}

	public Double getHistCompensacaoTotal() {
		return histCompensacaoTotal;
	}

	public void setHistCompensacaoTotal(Double histCompensacaoTotal) {
		this.histCompensacaoTotal = histCompensacaoTotal;
	}

	public TipoCompensacao getTipoCompensacaoCod() {
		return tipoCompensacaoCod;
	}

	public void setTipoCompensacaoCod(TipoCompensacao tipoCompensacaoCod) {
		this.tipoCompensacaoCod = tipoCompensacaoCod;
	}

	public BancoHoras getBancoHorasCod() {
		return bancoHorasCod;
	}

	public void setBancoHorasCod(BancoHoras bancoHorasCod) {
		this.bancoHorasCod = bancoHorasCod;
	}
	
	
}	
