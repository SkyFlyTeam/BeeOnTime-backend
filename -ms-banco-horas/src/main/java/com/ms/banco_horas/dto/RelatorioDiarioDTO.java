package com.ms.banco_horas.dto;

import java.time.LocalDate;

public class RelatorioDiarioDTO {
    private Long usuarioCod;
    private LocalDate data;
    private Double extrasPagas;
    private Double saldoAcumulado;
    private Double horasAbonadas;

    public Long getUsuarioCod() {
        return usuarioCod;
    }
    public void setUsuarioCod(Long usuarioCod) {
        this.usuarioCod = usuarioCod;
    }
    public Double getExtrasPagas() {
        return extrasPagas;
    }
    public void setExtrasPagas(Double extrasPagas) {
        this.extrasPagas = extrasPagas;
    }
    public Double getSaldoAcumulado() {
        return saldoAcumulado;
    }
    public void setSaldoAcumulado(Double saldoAcumulado) {
        this.saldoAcumulado = saldoAcumulado;
    }
    public Double getHorasAbonadas() {
        return horasAbonadas;
    }
    public void setHorasAbonadas(Double horasAbonadas) {
        this.horasAbonadas = horasAbonadas;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
}
