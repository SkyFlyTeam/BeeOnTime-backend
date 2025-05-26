package com.api.alerta.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.alerta.entidade.Alerta;

public interface AlertaRepositorio extends JpaRepository<Alerta, Long> {
    List<Alerta> findByTipoAlerta_TipoAlertaNome(String tipoAlertaNome);
}
