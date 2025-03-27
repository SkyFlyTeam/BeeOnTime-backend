package com.fatec.ms_usuario.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.ms_usuario.entidade.Empresa;

public interface EmpresaRepositorio extends JpaRepository<Empresa, Long> {
}
