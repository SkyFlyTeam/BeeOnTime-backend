package com.msponto.ms_ponto.dto;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 
 */
public class HorasComNomeDTO {
    private Long horasCod;
    private Float horasExtras;
    private Float horasTrabalhadas;
    private Float horasNoturnas;
    private Float horasFaltantes;
    private LocalDate horasData;
    private Long usuarioCod;
    private String usuarioNome;
    private Time jornada_horarioEntrada;
    private LocalTime horarioBatida;

	public Long getHorasCod() {
		return horasCod;
	}

	public void setHorasCod(Long horasCod) {
		this.horasCod = horasCod;
	}

	public Float getHorasExtras() {
		return horasExtras;
	}

	public void setHorasExtras(Float horasExtras) {
		this.horasExtras = horasExtras;
	}

	public Float getHorasTrabalhadas() {
		return horasTrabalhadas;
	}

	public void setHorasTrabalhadas(Float horasTrabalhadas) {
		this.horasTrabalhadas = horasTrabalhadas;
	}

	public Float getHorasNoturnas() {
		return horasNoturnas;
	}

	public void setHorasNoturnas(Float horasNoturnas) {
		this.horasNoturnas = horasNoturnas;
	}

	public Float getHorasFaltantes() {
		return horasFaltantes;
	}

	public void setHorasFaltantes(Float horasFaltantes) {
		this.horasFaltantes = horasFaltantes;
	}

	public LocalDate getHorasData() {
		return horasData;
	}

	public void setHorasData(LocalDate horasData) {
		this.horasData = horasData;
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

	public Time getJornada_horarioEntrada() {
		return jornada_horarioEntrada;
	}

	public void setJornada_horarioEntrada(Time jornada_horarioEntrada) {
		this.jornada_horarioEntrada = jornada_horarioEntrada;
	}

	public LocalTime getHorarioBatida() {
		return horarioBatida;
	}

	public void setHorarioBatida(LocalTime horarioBatida) {
		this.horarioBatida = horarioBatida;
	}
    	
    
}
