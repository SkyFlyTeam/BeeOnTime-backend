package com.fatec.ms_usuario.entidade;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "folga_tipo")
public class FolgaTipo {
	
	public FolgaTipo(String nome) {
		this.tipoFolgaNome = nome;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tipoFolgaCod;
	
	@Column(nullable= false)
	private String tipoFolgaNome;
	
	public FolgaTipo() {
    }

    @JsonCreator
    public FolgaTipo(@JsonProperty("tipoFolgaCod") Long tipoFolgaCod) {
        this.tipoFolgaCod = tipoFolgaCod;
    }

	public Long getTipoFolgaCod() {
		return tipoFolgaCod;
	}

	public void setTipoFolgaCod(Long tipoFolgaCod) {
		this.tipoFolgaCod = tipoFolgaCod;
	}

	public String getTipoFolgaNome() {
		return tipoFolgaNome;
	}

	public void setTipoFolgaNome(String tipoFolgaNome) {
		this.tipoFolgaNome = tipoFolgaNome;
	}
	
	
}