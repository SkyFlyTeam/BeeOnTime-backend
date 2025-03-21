package com.api.empresa.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.empresa.entidade.Empresa;

public interface EmpresaRepositorio extends JpaRepository<Empresa, Long> {
}