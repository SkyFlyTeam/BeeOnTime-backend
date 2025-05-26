package com.msponto.ms_ponto.repositorio.mysql;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msponto.ms_ponto.entidade.mysql.Falta;


public interface FaltaRepositorio extends JpaRepository<Falta, Long> {

    List<Falta> findByUsuarioCod(Long usuarioCod);
    Falta findByFaltaDiaAndUsuarioCod(LocalDate faltaDia, Long usuarioCod);
    List<Falta> findByFaltaDiaBetween(LocalDate starDate, LocalDate endDate);
}
