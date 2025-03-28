package com.msponto.ms_ponto.modelo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


import com.msponto.ms_ponto.dto.JornadaDTO;
import com.msponto.ms_ponto.dto.UsuarioDTO;

public class VerificadorDiaTrabalhado {

    public Boolean verificar(UsuarioDTO usuario, LocalDate dataAtual){
        JornadaDTO jornada = usuario.getJornada();
        DayOfWeek diaDaSemanaAtual = dataAtual.getDayOfWeek();

        List<String> diasSemana = Arrays.asList("SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY");
        
        Integer posicao = diasSemana.indexOf(diaDaSemanaAtual.toString());

        Boolean diaAtualNaJornada = jornada.getJornada_diasSemana().get(posicao);
        return diaAtualNaJornada;
    }
}
