package com.ms.banco_horas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.banco_horas.model.BancoHoras;

@Repository
public interface BancoHorasRepository extends JpaRepository<BancoHoras, Long> {

}
