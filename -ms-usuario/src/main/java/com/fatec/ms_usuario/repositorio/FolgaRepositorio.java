package com.fatec.ms_usuario.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fatec.ms_usuario.entidade.Folga;

public interface FolgaRepositorio extends JpaRepository<Folga, Long> {
}
