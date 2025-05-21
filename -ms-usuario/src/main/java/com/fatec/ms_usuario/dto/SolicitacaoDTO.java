package com.fatec.ms_usuario.dto;

import java.util.List;

public class SolicitacaoDTO {

    private Long solicitacaoCod;

    private String solicitacaoMensagem;

    // Usar lista de String para datas no formato ISO (ex: "2025-05-21")
    private List<String> solicitacaoDataPeriodo;

    private Long usuarioCod;

    private Double horasSolicitadas;

    private Integer solicitacaoStatus; // pode ser Enum ordinal, ou String conforme seu caso

    private Long tipoSolicitacaoCod;

    private String solicitacaoAnexoNome; // nome do arquivo enviado

    // O conteúdo binário do anexo não fica no DTO, será recebido via MultipartFile separado

    private String solicitacaoDevolutiva;

    // Getters e setters

    public Long getSolicitacaoCod() {
        return solicitacaoCod;
    }

    public void setSolicitacaoCod(Long solicitacaoCod) {
        this.solicitacaoCod = solicitacaoCod;
    }

    public String getSolicitacaoMensagem() {
        return solicitacaoMensagem;
    }

    public void setSolicitacaoMensagem(String solicitacaoMensagem) {
        this.solicitacaoMensagem = solicitacaoMensagem;
    }

    public List<String> getSolicitacaoDataPeriodo() {
        return solicitacaoDataPeriodo;
    }

    public void setSolicitacaoDataPeriodo(List<String> solicitacaoDataPeriodo) {
        this.solicitacaoDataPeriodo = solicitacaoDataPeriodo;
    }

    public Long getUsuarioCod() {
        return usuarioCod;
    }

    public void setUsuarioCod(Long usuarioCod) {
        this.usuarioCod = usuarioCod;
    }

    public Double getHorasSolicitadas() {
        return horasSolicitadas;
    }

    public void setHorasSolicitadas(Double horasSolicitadas) {
        this.horasSolicitadas = horasSolicitadas;
    }

    public Integer getSolicitacaoStatus() {
        return solicitacaoStatus;
    }

    public void setSolicitacaoStatus(Integer solicitacaoStatus) {
        this.solicitacaoStatus = solicitacaoStatus;
    }

    public Long getTipoSolicitacaoCod() {
        return tipoSolicitacaoCod;
    }

    public void setTipoSolicitacaoCod(Long tipoSolicitacaoCod) {
        this.tipoSolicitacaoCod = tipoSolicitacaoCod;
    }

    public String getSolicitacaoAnexoNome() {
        return solicitacaoAnexoNome;
    }

    public void setSolicitacaoAnexoNome(String solicitacaoAnexoNome) {
        this.solicitacaoAnexoNome = solicitacaoAnexoNome;
    }

    public String getSolicitacaoDevolutiva() {
        return solicitacaoDevolutiva;
    }

    public void setSolicitacaoDevolutiva(String solicitacaoDevolutiva) {
        this.solicitacaoDevolutiva = solicitacaoDevolutiva;
    }
}
