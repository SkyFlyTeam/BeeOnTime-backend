package com.msponto.ms_ponto.dto;

import java.time.LocalDate;

public class SolicitacaoDTO {
	
    private String solicitacaoMensagem;
    private Long usuarioCod;
    private Double horasSolicitadas;
    private SolicitacaoTipoDTO tipoSolicitacaoCod;
    private LocalDate SolicitacaoDataPeriodo;
    
	public String getSolicitacaoMensagem() {
		return solicitacaoMensagem;
	}
	public void setSolicitacaoMensagem(String solicitacaoMensagem) {
		this.solicitacaoMensagem = solicitacaoMensagem;
	}
	public Long getUsuarioCod() {
		return usuarioCod;
	}
	public void setUsuarioCod(Long usuarioCod) {
		this.usuarioCod = usuarioCod;
	}
	public Double getHorasSolicitadas() {
		return horasSolicitadas;
	}
	public void setHorasSolicitadas(Double horasSolicitadas) {
		this.horasSolicitadas = horasSolicitadas;
	}
	public SolicitacaoTipoDTO getTipoSolicitacaoCod() {
		return tipoSolicitacaoCod;
	}
	public void setTipoSolicitacaoCod(SolicitacaoTipoDTO tipoSolicitacaoCod) {
		this.tipoSolicitacaoCod = tipoSolicitacaoCod;
	}
	public LocalDate getSolicitacaoDataPeriodo() {
		return SolicitacaoDataPeriodo;
	}
	public void setSolicitacaoDataPeriodo(LocalDate solicitacaoDataPeriodo) {
		SolicitacaoDataPeriodo = solicitacaoDataPeriodo;
	} 

    

}
