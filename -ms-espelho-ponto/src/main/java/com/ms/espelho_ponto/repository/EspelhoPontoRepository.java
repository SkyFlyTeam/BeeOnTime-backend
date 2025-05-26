package com.ms.espelho_ponto.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.espelho_ponto.model.EspelhoPonto;

@Repository
public interface EspelhoPontoRepository extends JpaRepository<EspelhoPonto, Long> {
    EspelhoPonto findByUsuarioCod(Long usuarioCod);
    EspelhoPonto findByUsuarioCodAndEspelhoPontoDataGeracao(Long usuarioCod, LocalDate espelhoPontoDataGeracao);
}
