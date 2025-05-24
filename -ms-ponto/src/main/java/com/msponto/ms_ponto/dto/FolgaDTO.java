package com.msponto.ms_ponto.dto;
import java.time.LocalDate;
import java.util.List;

public class FolgaDTO {
    private Long folgaCod;
    private List<LocalDate> folgaDataPeriodo;
    private int folgaDiasUteis;
    private String folgaObservacao;
    private FolgaTipoDTO folgaTipo;
    private Long usuarioCod;

    // Getters e setters

    public Long getFolgaCod() {
        return folgaCod;
    }

    public void setFolgaCod(Long folgaCod) {
        this.folgaCod = folgaCod;
    }

    public List<LocalDate> getFolgaDataPeriodo() {
        return folgaDataPeriodo;
    }

    public void setFolgaDataPeriodo(List<LocalDate> folgaDataPeriodo) {
        this.folgaDataPeriodo = folgaDataPeriodo;
    }

    public int getFolgaDiasUteis() {
        return folgaDiasUteis;
    }

    public void setFolgaDiasUteis(int folgaDiasUteis) {
        this.folgaDiasUteis = folgaDiasUteis;
    }

    public String getFolgaObservacao() {
        return folgaObservacao;
    }

    public void setFolgaObservacao(String folgaObservacao) {
        this.folgaObservacao = folgaObservacao;
    }

    public FolgaTipoDTO getFolgaTipo() {
        return folgaTipo;
    }

    public void setFolgaTipo(FolgaTipoDTO folgaTipo) {
        this.folgaTipo = folgaTipo;
    }

    public Long getUsuarioCod() {
        return usuarioCod;
    }

    public void setUsuarioCod(Long usuarioCod) {
        this.usuarioCod = usuarioCod;
    }

    // Classe interna para FolgaTipoDTO

    public static class FolgaTipoDTO {
        private Long tipoFolgaCod;
        private String tipoFolgaNome;

        public Long getTipoFolgaCod() {
            return tipoFolgaCod;
        }

        public void setTipoFolgaCod(Long tipoFolgaCod) {
            this.tipoFolgaCod = tipoFolgaCod;
        }

        public String getTipoFolgaNome() {
            return tipoFolgaNome;
        }

        public void setTipoFolgaNome(String tipoFolgaNome) {
            this.tipoFolgaNome = tipoFolgaNome;
        }
    }
}
