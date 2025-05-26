package com.fatec.ms_usuario.entidade;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Feriado {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long feriadoCod;
	
	@Column
	private LocalDate feriadoData;

    @Column
	private String feriadoNome;

    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "empCod", referencedColumnName = "empCod", insertable = false, updatable = false)
	@JsonIgnore
	private Empresa empresa;

	private Long empCod;

    public Long getFeriadoCod() {
        return feriadoCod;
    }

    public void setFeriadoCod(Long feriadoCod) {
        this.feriadoCod = feriadoCod;
    }

    public LocalDate getFeriadoData() {
        return feriadoData;
    }

    public void setFeriadoData(LocalDate feriadoData) {
        this.feriadoData = feriadoData;
    }

    public String getFeriadoNome() {
        return feriadoNome;
    }

    public void setFeriadoNome(String feriadoNome) {
        this.feriadoNome = feriadoNome;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Long getEmpCod() {
        return empCod;
    }

    public void setEmpCod(Long empCod) {
        this.empCod = empCod;
    } 
}
