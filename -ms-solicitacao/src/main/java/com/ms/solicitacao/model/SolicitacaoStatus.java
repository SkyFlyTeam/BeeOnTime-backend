package com.ms.solicitacao.model;

public enum SolicitacaoStatus {
    PENDENTE(0),
    APROVADA(1),
    RECUSADA(2);

    private final int codigo;

    SolicitacaoStatus(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public static SolicitacaoStatus fromCodigo(int codigo) {
        for (SolicitacaoStatus status : SolicitacaoStatus.values()) {
            if (status.getCodigo() == codigo) {
                return status;
            }
        }
        throw new IllegalArgumentException("Código inválido: " + codigo);
    }
}
