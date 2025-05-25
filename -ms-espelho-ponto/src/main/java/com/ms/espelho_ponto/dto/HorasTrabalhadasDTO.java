package com.ms.espelho_ponto.dto;

import java.time.LocalDate;

public class HorasTrabalhadasDTO {
    private Long usuarioCod;
    private LocalDate data;
    private Double horasTotal;
    private Double desconto;

    // Getters and Setters
    public Long getUsuarioCod() {
        return usuarioCod;
    }

    public void setUsuarioCod(Long usuarioCod) {
        this.usuarioCod = usuarioCod;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Double getHorasTotal() {
        return horasTotal;
    }

    public void setHorasTotal(Double horasTotal) {
        this.horasTotal = horasTotal;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }
}
