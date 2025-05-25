package com.api.alerta.entidade;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class AlertaTipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tipoAlertaCod;

    @Column
    private String tipoAlertaNome;

    @OneToMany(mappedBy = "tipoAlerta")
    private List<Alerta> alertas;

    public AlertaTipo() {}

    public AlertaTipo(String tipoAlertaNome) {
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

    public List<Alerta> getAlertas() {
        return alertas;
    }

    public void setAlertas(List<Alerta> alertas) {
        this.alertas = alertas;
    }
}
