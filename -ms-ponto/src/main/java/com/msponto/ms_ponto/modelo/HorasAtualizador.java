package com.msponto.ms_ponto.modelo;


import com.msponto.ms_ponto.entidade.mysql.Horas;

public class HorasAtualizador {

    // Método para atualizar os dados das horas
    public void atualizarDados(Horas horas, Horas horasAtualizacao) {
        if (horasAtualizacao.getHorasExtras() != null) {
            horas.setHorasExtras(horasAtualizacao.getHorasExtras());
        }

        if (horasAtualizacao.getHorasTrabalhadas() != null) {
            horas.setHorasTrabalhadas(horasAtualizacao.getHorasTrabalhadas());
        }

        if (horasAtualizacao.getHorasNoturnas() != null) {
            horas.setHorasNoturnas(horasAtualizacao.getHorasNoturnas());
        }

        if (horasAtualizacao.getHorasFaltantes() != null) {
            horas.setHorasFaltantes(horasAtualizacao.getHorasFaltantes());
        }

        if (horasAtualizacao.getHorasData() != null) {
            horas.setHorasData(horasAtualizacao.getHorasData());
        }

        if (horasAtualizacao.getUsuarioCod() != null) {
            horas.setUsuarioCod(horasAtualizacao.getUsuarioCod());
        }
    }
}