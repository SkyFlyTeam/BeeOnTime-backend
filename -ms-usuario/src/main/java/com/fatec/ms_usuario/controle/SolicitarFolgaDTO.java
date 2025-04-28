package com.fatec.ms_usuario.controle;

import java.util.List;

import com.fatec.ms_usuario.entidade.FolgaTipo;
import com.fatec.ms_usuario.entidade.Usuario;

public class SolicitarFolgaDTO {
    private String folObservacao;
    private FolgaTipo folgaTipo;
    private Usuario usuario;
    private List<String> folDataPeriodo;

    // Getters e Setters

    public String getFolObservacao() {
        return folObservacao;
    }

    public void setFolObservacao(String folObservacao) {
        this.folObservacao = folObservacao;
    }

    public FolgaTipo getFolgaTipo() {
        return folgaTipo;
    }

    public void setFolgaTipo(FolgaTipo folgaTipo) {
        this.folgaTipo = folgaTipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<String> getFolDataPeriodo() {
        return folDataPeriodo;
    }

    public void setFolDataPeriodo(List<String> folDataPeriodo) {
        this.folDataPeriodo = folDataPeriodo;
    }
}
