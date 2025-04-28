package com.fatec.ms_usuario.dto;

import java.util.List;

import com.fatec.ms_usuario.entidade.FolgaTipo;
import com.fatec.ms_usuario.entidade.Usuario;

public class SolicitacaoFolgaDTO {
    private String folObservacao;
    private FolgaTipo folgaTipo;
    private Usuario usuario;
    private List<String> folDataPeriodo;
    
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
