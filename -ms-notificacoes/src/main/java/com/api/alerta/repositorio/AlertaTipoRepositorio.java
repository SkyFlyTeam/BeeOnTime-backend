package com.api.alerta.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.alerta.entidade.AlertaTipo;

public interface AlertaTipoRepositorio extends JpaRepository<AlertaTipo, Long> {
}
