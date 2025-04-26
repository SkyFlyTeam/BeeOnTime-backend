package com.ms.banco_horas.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ms.banco_horas.model.ExtrasPagas;


public interface ExtrasPagasRepositorty extends JpaRepository<ExtrasPagas, Long> {
    @Query("SELECT e FROM ExtrasPagas e WHERE e.usuarioCod = :usuarioCod AND e.extrasPagasData <= :data ORDER BY e.extrasPagasData DESC")
    List<ExtrasPagas> findTopByUsuarioCodAndDataLessThanEqualOrderByDataDesc(@Param("usuarioCod") Long usuarioCod, @Param("data") LocalDate data);

    ExtrasPagas findByExtrasPagasDataAndUsuarioCod(LocalDate extrasPagasData, Long usuarioCod);
}
