package com.fatec.ms_usuario.entidade;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Setor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long setorCod;

    @Column
    private String setorNome;

    @OneToMany(mappedBy = "setor")
    private List<Usuario> usuarios;

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