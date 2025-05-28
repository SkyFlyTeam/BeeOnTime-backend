package com.api.alerta.dto;

import java.util.Date;

public class AlertaDTO {
    private long alertaCod;
    private String alertaMensagem;
    private Date alertaDataCriacao;
    private AlertaTipoDTO tipoAlerta;
    private String alertaSetorDirecionado;
    private int alertaUserAlvo;

    // Getters e Setters
    public long getAlertaCod() {
        return alertaCod;
    }

    public void setAlertaCod(long alertaCod) {
        this.alertaCod = alertaCod;
    }

    public String getAlertaMensagem() {
        return alertaMensagem;
    }

    public void setAlertaMensagem(String alertaMensagem) {
        this.alertaMensagem = alertaMensagem;
    }

    public Date getAlertaDataCriacao() {
        return alertaDataCriacao;
    }

    public void setAlertaDataCriacao(Date alertaDataCriacao) {
        this.alertaDataCriacao = alertaDataCriacao;
    }

    public AlertaTipoDTO getTipoAlerta() {
        return tipoAlerta;
    }

    public void setTipoAlerta(AlertaTipoDTO tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
    }

    public String getAlertaSetorDirecionado() {
        return alertaSetorDirecionado;
    }

    public void setAlertaSetorDirecionado(String alertaSetorDirecionado) {
        this.alertaSetorDirecionado = alertaSetorDirecionado;
    }

    public int getAlertaUserAlvo() {
        return alertaUserAlvo;
    }

    public void setAlertaUserAlvo(int alertaUserAlvo) {
        this.alertaUserAlvo = alertaUserAlvo;
    }

    // Classe interna AlertaTipoDTO
    public static class AlertaTipoDTO {
        private long tipoAlertaCod;
        private String tipoAlertaNome;

        public AlertaTipoDTO(long tipoAlertaCod, String tipoAlertaNome) {
            this.tipoAlertaCod = tipoAlertaCod;
            this.tipoAlertaNome = tipoAlertaNome;
        }

        public long getTipoAlertaCod() {
            return tipoAlertaCod;
        }

        public void setTipoAlertaCod(long tipoAlertaCod) {
            this.tipoAlertaCod = tipoAlertaCod;
        }

        public String getTipoAlertaNome() {
            return tipoAlertaNome;
        }

        public void setTipoAlertaNome(String tipoAlertaNome) {
            this.tipoAlertaNome = tipoAlertaNome;
        }
    }
}
