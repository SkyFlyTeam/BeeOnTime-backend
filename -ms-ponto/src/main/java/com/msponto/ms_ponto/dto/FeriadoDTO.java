package com.msponto.ms_ponto.dto;

import java.time.LocalDate;

public class FeriadoDTO {
    private Long feriadoCod;
    private String feriadoNome;
    private LocalDate feriadoData;
    private Long empCod;
    
    public Long getFeriadoCod() {
        return feriadoCod;
    }
    public void setFeriadoCod(Long feriadoCod) {
        this.feriadoCod = feriadoCod;
    }
    public String getFeriadoNome() {
        return feriadoNome;
    }
    public void setFeriadoNome(String feriadoNome) {
        this.feriadoNome = feriadoNome;
    }
    public LocalDate getFeriadoData() {
        return feriadoData;
    }
    public void setFeriadoData(LocalDate feriadoData) {
        this.feriadoData = feriadoData;
    }
    public Long getEmpCod() {
        return empCod;
    }
    public void setEmpCod(Long empCod) {
        this.empCod = empCod;
    }

    
}
