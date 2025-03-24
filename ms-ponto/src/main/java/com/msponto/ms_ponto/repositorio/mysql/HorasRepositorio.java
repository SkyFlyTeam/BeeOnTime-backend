package com.msponto.ms_ponto.repositorio.mysql;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msponto.ms_ponto.entidade.mysql.Horas;

public interface HorasRepositorio extends JpaRepository<Horas, Long>{
    List<Horas> findByUsuarioCod(Long usuarioCod);

    List<Horas> findByUsuarioCodAndHorasData(Long usuarioCod, LocalDate horasData);

    List<Horas> findByUsuarioCodAndHorasDataBetween(Long usuarioCod, LocalDate starDate, LocalDate enDate);

    Horas findByHorasData(LocalDate horasData);
}
