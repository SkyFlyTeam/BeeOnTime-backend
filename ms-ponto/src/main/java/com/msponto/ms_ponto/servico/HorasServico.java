package com.msponto.ms_ponto.servico;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msponto.ms_ponto.entidade.mysql.Horas;
import com.msponto.ms_ponto.repositorio.mysql.HorasRepositorio;

@Service
public class HorasServico {
    @Autowired
    private HorasRepositorio horas_repo;

    public List<Horas> getUsuarioHoras(Long usuario_cod){
        List<Horas> usuario_horas = horas_repo.findByUsuarioCod(usuario_cod);
        return usuario_horas;
    }
    
    public List<Horas> getUsuarioHorasByDate(Long usuario_cod, LocalDate horas_data){
        List<Horas> usuario_horas = horas_repo.findByUsuarioCodAndHorasData(usuario_cod, horas_data);
        return usuario_horas;
    }

    public List<Horas> getUsuarioHorasByPeriod(Long usuario_cod, LocalDate start_date, LocalDate end_date){
        List<Horas> usuario_horas = horas_repo.findByUsuarioCodAndHorasDataBetween(usuario_cod, start_date, end_date);
        return usuario_horas;
    }

    public Horas getHorasCodByDate(LocalDate date){
        Horas horas = horas_repo.findByHorasData(date);
        return horas;
    }

    public Optional<Horas> getHorasByCod(Long horas_cod){
        Optional<Horas> horas = horas_repo.findById(horas_cod);
        return horas;
    }

    public Boolean createHoras(Horas horas){
        try{
            horas_repo.save(horas);
            return true;
        } catch(Exception e){
            return false;
        }
    }
}
