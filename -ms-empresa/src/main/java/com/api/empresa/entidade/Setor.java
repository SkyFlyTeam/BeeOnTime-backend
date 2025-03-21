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
    private long setor_cod;

    @Column
    private String setor_nome;

    // Getters and Setters

    public long getSetor_cod() {
        return setor_cod;
    }

    public void setSetor_cod(long setor_cod) {
        this.setor_cod = setor_cod;
    }

    public String getSetor_nome() {
        return setor_nome;
    }

    public void setSetor_nome(String setor_nome) {
        this.setor_nome = setor_nome;
    }
}