package com.msponto.ms_ponto.dto;

public class RelatorioHorasDTO {
    private Long usuarioCod;
    private Float horasTotal;
    private Float desconto;

    public Long getUsuarioCod() {
        return usuarioCod;
    }
    public void setUsuarioCod(Long usuarioCod) {
        this.usuarioCod = usuarioCod;
    }
    public Float getHorasTotal() {
        return horasTotal;
    }
    public void setHorasTotal(Float horasTotal) {
        this.horasTotal = horasTotal;
    }
    public Float getDesconto() {
        return desconto;
    }
    public void setDesconto(Float desconto) {
        this.desconto = desconto;
    }
}
