package com.msponto.ms_ponto.entidade.mysql;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Atraso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long atrasoCod;

    @Column
    private Float atrasoTempo;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "horas_cod", referencedColumnName = "horasCod", nullable = true)
    @JsonIgnoreProperties("atraso") // Ignora a serialização de 'atraso' em Horas
    private Horas horas;

    public Long getAtrasoCod() {
        return atrasoCod;
    }

    public void setAtrasoCod(Long atrasoCod) {
        this.atrasoCod = atrasoCod;
    }

    public Float getAtrasoTempo() {
        return atrasoTempo;
    }

    public void setAtrasoTempo(Float atrasoTempo) {
        this.atrasoTempo = atrasoTempo;
    }
    
    public Horas getHoras() {
        return horas; 
    }

    public void setHoras(Horas horas) {
        this.horas = horas;
    }
}
