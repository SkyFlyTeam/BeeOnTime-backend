package com.msponto.ms_ponto.dto;

import java.time.LocalDate;

public class PeriodoDTO {
    private String dataInicio;
    private String dataFim;

    public String getDataInicio() {
        return dataInicio;
    }
    public void setaDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }
    public String getDataFim() {
        return dataFim;
    }
    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    } 

    public LocalDate getDataInicioAsDate() {
        return LocalDate.parse(this.dataInicio);
    }

    public LocalDate getDataFimAsDate() {
        return LocalDate.parse(this.dataFim);
    }
}
