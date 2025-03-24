package com.msponto.ms_ponto.enums;

public enum TipoPonto {
    ENTRADA(0),
    SAIDA(1),
    ALMOCO(2);

    private final int tipo;

    TipoPonto(int tipo){
        this.tipo = tipo;
    }

    public int getTipo(){
        return tipo;
    }

    public static String getTipofromInt(int i){
        for(TipoPonto tipo : TipoPonto.values()){
            if(tipo.getTipo() == i){
                return tipo.name();
            }
        }
        throw new IllegalArgumentException("Tipo de ponto inválido: " + i);
    }
}
