package com.api.empresa.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.empresa.entidade.Setor;

public interface SetorRepositorio extends JpaRepository<Setor, Long> {
}