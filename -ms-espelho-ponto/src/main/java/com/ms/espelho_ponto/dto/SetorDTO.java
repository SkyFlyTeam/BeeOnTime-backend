package com.ms.espelho_ponto.dto;

public class SetorDTO {
    private int setorCod;
    private String setorNome;
    private int empCod;

    // Getters and setters
    public int getSetorCod() {
        return setorCod;
    }

    public void setSetorCod(int setorCod) {
        this.setorCod = setorCod;
    }

    public String getSetorNome() {
        return setorNome;
    }

    public void setSetorNome(String setorNome) {
        this.setorNome = setorNome;
    }

    public int getEmpCod() {
        return empCod;
    }

    public void setEmpCod(int empCod) {
        this.empCod = empCod;
    }
}
