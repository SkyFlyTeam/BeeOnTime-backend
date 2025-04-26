package com.ms.banco_horas.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ms.banco_horas.model.BancoHoras;

@Repository
public interface BancoHorasRepository extends JpaRepository<BancoHoras, Long> {
	BancoHoras findByUsuarioCod(Long usuarioCod);
	BancoHoras findByUsuarioCodAndBancoHorasData(Long usuarioCod, LocalDate bancoHorasData);
    List<BancoHoras> findByUsuarioCodAndBancoHorasDataBetween(Long usuarioCod, LocalDate starDate, LocalDate enDate);
    @Query("SELECT b FROM BancoHoras b WHERE b.usuarioCod = :usuarioCod AND b.bancoHorasData <= :data ORDER BY b.bancoHorasData DESC")
    List<BancoHoras> findTopByUsuarioCodAndBancoHorasDataLessThanEqualOrderByBancoHorasDataDesc(@Param("usuarioCod") Long usuarioCod, @Param("data") LocalDate data);
}
