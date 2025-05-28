package com.api.alerta.entidade;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Alerta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long alertaCod;

    @Column
    private String alertaMensagem;

    @Column
    private Date alertaDataCriacao;

    @Column
    private String alertaSetorDirecionado;

    @Column
    private int alertaUserAlvo;

    @ManyToOne
    @JoinColumn(name = "tipoAlertaCod", nullable = false)
    @JsonBackReference
    private AlertaTipo tipoAlerta;

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

    public AlertaTipo getTipoAlerta() {
        return tipoAlerta;
    }

    public void setTipoAlerta(AlertaTipo tipoAlerta) {
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
}
