package com.msponto.ms_ponto.entidade.mysql;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"usuarioCod", "faltaDia"}))
public class Falta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long FaltaCod;

    @Column
    private LocalDate faltaDia;

    @Column
    private String faltaJustificativa;

    @Column 
    private Long usuarioCod;

    public Long getFaltaCod() {
		return FaltaCod;
	}

	public void setFaltaCod(Long faltaCod) {
		FaltaCod = faltaCod;
	}

	public LocalDate getFaltaDia() {
		return faltaDia;
	}

	public void setFaltaDia(LocalDate dataAnterior) {
		this.faltaDia = dataAnterior;
	}

	public String getFaltaJustificativa() {
		return faltaJustificativa;
	}

	public void setFaltaJustificativa(String faltaJustificativa) {
		this.faltaJustificativa = faltaJustificativa;
	}

	public Long getUsuarioCod() {
		return usuarioCod;
	}

	public void setUsuarioCod(Long usuarioCod) {
		this.usuarioCod = usuarioCod;
	}

}
