package com.msponto.ms_ponto.dto;

import java.sql.Time;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.ElementCollection;

public class JornadaDTO {

    private Long jornada_cod;
	
	private Boolean jornada_horarioFlexivel;
	
	@ElementCollection
	private List<Boolean> jornada_diasSemana;
	
	private Time jornada_horarioEntrada;
	
	private Time jornada_horarioSaida;

	private Float jornada_horarioAlmoco;
	
	@JsonIgnore
	private UsuarioDTO usuario;

    private Long usuario_cod;

	public Long getJornada_cod() {
		return jornada_cod;
	}

	public void setJornada_cod(Long jornada_cod) {
		this.jornada_cod = jornada_cod;
	}

	public Boolean getJornada_horarioFlexivel() {
		return jornada_horarioFlexivel;
	}

	public void setJornada_horarioFlexivel(Boolean jornada_horarioFlexivel) {
		this.jornada_horarioFlexivel = jornada_horarioFlexivel;
	}

	public List<Boolean> getJornada_diasSemana() {
		return jornada_diasSemana;
	}

	public void setJornada_diasSemana(List<Boolean> jornada_diasSemana) {
		this.jornada_diasSemana = jornada_diasSemana;
	}

	public Time getJornada_horarioEntrada() {
		return jornada_horarioEntrada;
	}

	public void setJornada_horarioEntrada(Time jornada_horarioEntrada) {
		this.jornada_horarioEntrada = jornada_horarioEntrada;
	}

	public Time getJornada_horarioSaida() {
		return jornada_horarioSaida;
	}

	public void setJornada_horarioSaida(Time jornada_horarioSaida) {
		this.jornada_horarioSaida = jornada_horarioSaida;
	}

	public Long getUsuario_cod() {
        return usuario_cod;
    }

    public void setUsuario_cod(Long usuario_cod) {
        this.usuario_cod = usuario_cod;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

	public float getJornada_horarioAlmoco() {
		return jornada_horarioAlmoco;
	}

	public void setJornada_horarioAlmoco(Float jornada_horarioAlmoco) {
		this.jornada_horarioAlmoco = jornada_horarioAlmoco;
	}
}
