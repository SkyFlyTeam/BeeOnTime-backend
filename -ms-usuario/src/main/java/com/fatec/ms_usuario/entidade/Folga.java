package com.fatec.ms_usuario.entidade;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Folga {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long folCod;
	
	@ElementCollection
	@CollectionTable(name = "folga_data_periodo", joinColumns = @JoinColumn(name = "folga_id"))
	@Column(name = "data")
	private List<LocalDate> folDataPeriodo;
	
	@Column
	private String folObservacao;
	
	@Column
	private Integer folDiasUteis;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_cod")
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "folTipoCod")
	private FolgaTipo folgaTipo;

	public long getFolCod() {
		return folCod;
	}

	public void setFolCod(long folCod) {
		this.folCod = folCod;
	}

	public List<LocalDate> getFolDataPeriodo() {
		return folDataPeriodo;
	}

	public void setFolDataPeriodo(List<LocalDate> folDataPeriodo) {
		this.folDataPeriodo = folDataPeriodo;
	}

	public String getFolObservacao() {
		return folObservacao;
	}

	public void setFolObservacao(String folObservacao) {
		this.folObservacao = folObservacao;
	}

	public Integer getFolDiasUteis() {
		return folDiasUteis;
	}

	public void setFolDiasUteis(Integer folDiasUteis) {
		this.folDiasUteis = folDiasUteis;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public FolgaTipo getFolgaTipo() {
        return folgaTipo;
    }

    public void setFolgaTipo(FolgaTipo folgaTipo) {
        this.folgaTipo = folgaTipo;
    }

}
