package com.ms.espelho_ponto.dto;

import java.util.List;

public class PontoDTO {
    private String id;
    private Long usuarioCod;
    private int horasCod;
    private String data; // If it's a string in the JSON, keep it as String or use LocalDate if you want to parse it
    private List<PontoEntryDTO> pontos;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUsuarioCod() {
        return usuarioCod;
    }

    public void setUsuarioCod(Long usuarioCod) {
        this.usuarioCod = usuarioCod;
    }

    public int getHorasCod() {
        return horasCod;
    }

    public void setHorasCod(int horasCod) {
        this.horasCod = horasCod;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<PontoEntryDTO> getPontos() {
        return pontos;
    }

    public void setPontos(List<PontoEntryDTO> pontos) {
        this.pontos = pontos;
    }
}

