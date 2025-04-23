package com.msponto.ms_ponto.dto;

public class SolicitacaoTipoDTO {

    private Long tipoSolicitacaoCod;
    private String tipoSolicitacaoNome;

    // Getters e Setters
    public Long getTipoSolicitacaoCod() {
        return tipoSolicitacaoCod;
    }

    public void setTipoSolicitacaoCod(Long tipoSolicitacaoCod) {
        this.tipoSolicitacaoCod = tipoSolicitacaoCod;
    }

    public String getTipoSolicitacaoNome() {
        return tipoSolicitacaoNome;
    }

    public void setTipoSolicitacaoNome(String tipoSolicitacaoNome) {
        this.tipoSolicitacaoNome = tipoSolicitacaoNome;
    }
}
