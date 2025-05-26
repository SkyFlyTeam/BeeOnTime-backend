package com.ms.espelho_ponto.dto;

import java.time.LocalTime;

public class PontoEntryDTO {
    private LocalTime horarioPonto; // LocalTime is used to represent time values
    private int tipoPonto;

    // Getters and Setters
    public LocalTime getHorarioPonto() {
        return horarioPonto;
    }

    public void setHorarioPonto(LocalTime horarioPonto) {
        this.horarioPonto = horarioPonto;
    }

    public int getTipoPonto() {
        return tipoPonto;
    }

    public void setTipoPonto(int tipoPonto) {
        this.tipoPonto = tipoPonto;
    }
}
