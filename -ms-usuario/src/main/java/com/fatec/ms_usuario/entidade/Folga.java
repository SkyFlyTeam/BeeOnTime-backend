package com.fatec.ms_usuario.entidade;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

@Entity
public class Folga {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long folgaCod;
	
	@ElementCollection
	@Column(name = "folga_data")
	private List<LocalDate> folgaDataPeriodo;
	
	@Column
	private String folgaObservacao;	
	
	@Column
	private int folgaDiasUteis;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_cod")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Usuario usuarioCod;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "folTipoCod")
	private FolgaTipo folgaTipo;
	
	@JsonProperty("usuarioCod")
    public Long getUsuarioCodId() {
        return usuarioCod != null ? usuarioCod.getUsuario_cod() : null;
    }

	public Long getFolgaCod() {
		return folgaCod;
	}

	public void setFolgaCod(Long folgaCod) {
		this.folgaCod = folgaCod;
	}

	public List<LocalDate> getFolgaDataPeriodo() {
		return folgaDataPeriodo;
	}

	public void setFolgaDataPeriodo(List<LocalDate> folgaDataPeriodo) {
		this.folgaDataPeriodo = folgaDataPeriodo;
	}

	public String getFolgaObservacao() {
		return folgaObservacao;
	}

	public void setFolgaObservacao(String folgaObservacao) {
		this.folgaObservacao = folgaObservacao;
	}

	public int getFolgaDiasUteis() {
		return folgaDiasUteis;
	}

	public void setFolgaDiasUteis(int folgaDiasUteis) {
		this.folgaDiasUteis = folgaDiasUteis;
	}

	public Usuario getUsuarioCod() {
		return usuarioCod;
	}

	public void setUsuarioCod(Usuario usuarioCod) {
		this.usuarioCod = usuarioCod;
	}

	public FolgaTipo getFolgaTipo() {
		return folgaTipo;
	}

	public void setFolgaTipo(FolgaTipo folgaTipo) {
		this.folgaTipo = folgaTipo;
	}

	
	
}