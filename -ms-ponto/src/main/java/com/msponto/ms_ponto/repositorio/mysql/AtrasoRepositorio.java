package com.msponto.ms_ponto.repositorio.mysql;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msponto.ms_ponto.entidade.mysql.Atraso;

public interface AtrasoRepositorio extends JpaRepository<Atraso, Long>{
	Optional<Atraso> findByHoras_HorasCod(Long horasCod);
}
