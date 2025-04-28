package com.fatec.ms_usuario.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.ms_usuario.entidade.Setor;

public interface SetorRepositorio extends JpaRepository<Setor, Long> {
    List<Setor> findByEmpCod(Long empCod);
}