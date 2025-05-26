package com.ms.espelho_ponto.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class EspelhoPonto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "espelhoPonto_cod")
    private long espelhoPontoCod;

    @Column(name = "espelhoPonto_mes")
    private String espelhoPontoMes;

    @Column(name = "espelhoPonto_url")
    private String espelhoPontoUrl;

    @Column(name = "espelhoPonto_assinado")
    private boolean espelhoPontoAssinado;

    @Column(name = "espelhoPonto_dataGeracao")
    private Date espelhoPontoDataGeracao;

	@Column(name = "espelhoPonto_dataAssinatura")
	private Date espelhoPontoDataAssinatura;

    @Column(name = "usuario_cod", nullable=false)
    private Long usuarioCod;

    // Getters & Setters

	public long getEspelhoPontoCod() {
		return espelhoPontoCod;
	}

	public void setEspelhoPontoCod(long espelhoPontoCod) {
		this.espelhoPontoCod = espelhoPontoCod;
	}

	public String getEspelhoPontoMes() {
		return espelhoPontoMes;
	}

	public void setEspelhoPontoMes(String espelhoPontoMes) {
		this.espelhoPontoMes = espelhoPontoMes;
	}

	public String getEspelhoPontoUrl() {
		return espelhoPontoUrl;
	}

	public void setEspelhoPontoUrl(String espelhoPontoUrl) {
		this.espelhoPontoUrl = espelhoPontoUrl;
	}

	public boolean isEspelhoPontoAssinado() {
		return espelhoPontoAssinado;
	}

	public void setEspelhoPontoAssinado(boolean espelhoPontoAssinado) {
		this.espelhoPontoAssinado = espelhoPontoAssinado;
	}

	public Date getEspelhoPontoDataGeracao() {
		return espelhoPontoDataGeracao;
	}

	public void setEspelhoPontoDataGeracao(Date espelhoPontoDataGeracao) {
		this.espelhoPontoDataGeracao = espelhoPontoDataGeracao;
	}

	public Date getEspelhoPontoDataAssinatura() {
		return espelhoPontoDataAssinatura;
	}

	public void setEspelhoPontoDataAssinatura(Date espelhoPontoDataAssinatura) {
		this.espelhoPontoDataAssinatura = espelhoPontoDataAssinatura;
	}

	public Long getUsuarioCod() {
		return usuarioCod;
	}

	public void setUsuarioCod(Long usuarioCod) {
		this.usuarioCod = usuarioCod;
	}
}
