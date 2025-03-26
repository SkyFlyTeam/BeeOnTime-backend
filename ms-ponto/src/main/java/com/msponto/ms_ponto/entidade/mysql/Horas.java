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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"usuarioCod", "horasData"}))
public class Horas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long horasCod;

    @Column
    private Float horasExtras;

    @Column
    private Float horasTrabalhadas;

    @Column
    private Float horasNoturnas;

    @Column
    private Float horasFaltantes;

    @Column
    private LocalDate horasData;

    @Column
    private Long usuarioCod;

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
}
