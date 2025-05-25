package com.ms.espelho_ponto.dto;

public class BancoHorasDTO {
    private Long usuarioCod;
    private Double extrasPagas;
    private Double saldoAcumulado;
    private Double horasAbonadas;

    // Getters and setters
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
}
