package com.api.empresa.entidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Setor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long setorCod;

    @Column
    private String setorNome;

    // Getters and Setters

    public long getSetorCod() {
        return setorCod;
    }

    public void setSetorCod(long setorCod) {
        this.setorCod = setorCod;
    }

    public String getSetorNome() {
        return setorNome;
    }

    public void setSetorNome(String setorNome) {
        this.setorNome = setorNome;
    }
}