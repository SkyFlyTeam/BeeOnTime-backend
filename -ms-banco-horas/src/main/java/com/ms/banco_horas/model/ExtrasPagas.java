package com.ms.banco_horas.model;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class ExtrasPagas {
	
	public ExtrasPagas() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "extrasPagas_cod")
	private long extrasPagasCod;
	
	@Column(name = "extrasPagas_saldoAtual")
	private Double extrasPagasSaldoAtual;
	
	@Column(name = "extrasPagas_data")
	private LocalDate extrasPagasData;
	
	@Column(name = "usuario_cod", nullable=false)
    private Long usuarioCod;
	
	@Transient
    private String usuarioNome;
	
	@Transient
    private String usuarioCargo;
    
    @Transient
    private int setorCod;
    
    @Transient
    private int nivelAcesso_cod;

	public long getExtrasPagasCod() {
		return extrasPagasCod;
	}

	public void setExtrasPagasCod(long extrasPagasCod) {
		this.extrasPagasCod = extrasPagasCod;
	}

	public Double getExtrasPagasSaldoAtual() {
		return extrasPagasSaldoAtual;
	}

	public void setExtrasPagasSaldoAtual(Double extrasPagasSaldoAtual) {
		this.extrasPagasSaldoAtual = extrasPagasSaldoAtual;
	}

	public LocalDate getExtrasPagasData() {
		return extrasPagasData;
	}

	public void setExtrasPagasData(LocalDate extrasPagasData) {
		this.extrasPagasData = extrasPagasData;
	}

	public Long getUsuarioCod() {
		return usuarioCod;
	}

	public void setUsuarioCod(Long usuarioCod) {
		this.usuarioCod = usuarioCod;
	}

	public String getUsuarioNome() {
		return usuarioNome;
	}

	public void setUsuarioNome(String usuarioNome) {
		this.usuarioNome = usuarioNome;
	}

	public String getUsuarioCargo() {
		return usuarioCargo;
	}

	public void setUsuarioCargo(String usuarioCargo) {
		this.usuarioCargo = usuarioCargo;
	}

	public int getSetorCod() {
		return setorCod;
	}

	public void setSetorCod(int setorCod) {
		this.setorCod = setorCod;
	}

	public int getNivelAcesso_cod() {
		return nivelAcesso_cod;
	}

	public void setNivelAcesso_cod(int nivelAcesso_cod) {
		this.nivelAcesso_cod = nivelAcesso_cod;
	}
	
	
}
