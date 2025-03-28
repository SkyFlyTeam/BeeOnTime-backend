package com.msponto.ms_ponto.entidade.mysql;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Atraso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long atrasoCod;

    @Column
    private Float atrasoTempo;

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
}
