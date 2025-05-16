package com.fatec.ms_usuario.repositorio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.ms_usuario.entidade.Feriado;

public interface FeriadoRepositorio extends JpaRepository<Feriado, Long> {
    List<Feriado> findByEmpCod(Long empCod);
    Optional<Feriado> findByEmpCodAndFeriadoData(Long empCod, LocalDate data);
}
