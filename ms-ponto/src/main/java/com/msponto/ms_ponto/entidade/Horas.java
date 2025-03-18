package com.msponto.ms_ponto.entidade;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Horas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long horas_cod;

    @Column
    private Float horas_extras;

    @Column
    private Float horas_trabalhadas;

    @Column
    private Float horas_noturnas;

    @Column
    private Float horas_faltantes;

    @Column
    private LocalDate horas_data;

    public Long getHoras_cod() {
        return horas_cod;
    }

    public void setHoras_cod(Long horas_cod) {
        this.horas_cod = horas_cod;
    }

    public Float getHoras_extras() {
        return horas_extras;
    }

    public void setHoras_extras(Float horas_extras) {
        this.horas_extras = horas_extras;
    }

    public Float getHoras_trabalhadas() {
        return horas_trabalhadas;
    }

    public void setHoras_trabalhadas(Float horas_trabalhadas) {
        this.horas_trabalhadas = horas_trabalhadas;
    }

    public Float getHoras_noturnas() {
        return horas_noturnas;
    }

    public void setHoras_noturnas(Float horas_noturnas) {
        this.horas_noturnas = horas_noturnas;
    }

    public Float getHoras_faltantes() {
        return horas_faltantes;
    }

    public void setHoras_faltantes(Float horas_faltantes) {
        this.horas_faltantes = horas_faltantes;
    }

    public LocalDate getHoras_data() {
        return horas_data;
    }

    public void setHoras_data(LocalDate horas_data) {
        this.horas_data = horas_data;
    }
}
