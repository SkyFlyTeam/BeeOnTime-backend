package com.msponto.ms_ponto.modelo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msponto.ms_ponto.dto.FeriadoDTO;
import com.msponto.ms_ponto.dto.JornadaDTO;
import com.msponto.ms_ponto.dto.UsuarioDTO;
import com.msponto.ms_ponto.ms_clients.UsuarioClient;

@Component
public class VerificadorDiaTrabalhado {

    @Autowired
    private UsuarioClient usuarioClient;

    public Boolean verificar(UsuarioDTO usuario, LocalDate dataAtual) {
        JornadaDTO jornada = usuario.getJornada();
    
        if (jornada == null || jornada.getJornada_diasSemana() == null) {
            return false;
        }
    
        DayOfWeek diaDaSemanaAtual = dataAtual.getDayOfWeek();
        List<String> diasSemana = Arrays.asList("SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY");
    
        Integer posicao = diasSemana.indexOf(diaDaSemanaAtual.toString());
    
        // Verifica se a posição é válida
        if (posicao >= 0 && posicao < jornada.getJornada_diasSemana().size()) {
            Boolean diaAtualNaJornada = jornada.getJornada_diasSemana().get(posicao);
            return diaAtualNaJornada;
        }
    
        return false;
    }

    public Boolean verificarFeriado(Long empCod, LocalDate dataAtual) {
        Optional<FeriadoDTO> feriado = usuarioClient.getFeriadoByEmpCodAndDate(empCod, dataAtual);
    
        return feriado.isPresent();
    }
}
