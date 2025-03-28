package com.msponto.ms_ponto.enums;

public enum SolicitacaoDecisao {
    APROVADA(0),
    RECUSADA(1);

    private final int decisao;

    SolicitacaoDecisao(int decisao){
        this.decisao = decisao;
    }

    public int getDecisao(){
        return decisao;
    }

    
    public static String getDecisaofromInt(int i){
        for(SolicitacaoDecisao decisao : SolicitacaoDecisao.values()){
            if(decisao.getDecisao() == i){
                return decisao.name();
            }
        }
        throw new IllegalArgumentException("Decisão inválido: " + i);
    }
}
