package com.ms.solicitacao.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SolicitacaoDTO {
    private Integer solicitacaoCod;
    private String solicitacaoMensagem;
    private String solicitacaoDevolutiva;
    private LocalDate solicitacaoDataPeriodo;
    private Integer tipoSolicitacaoCod;
}