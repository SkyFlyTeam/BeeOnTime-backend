package com.fatec.ms_usuario.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.ms_usuario.entidade.Jornada;
import com.fatec.ms_usuario.entidade.Usuario;

public interface JornadaRepositorio extends JpaRepository<Jornada, Long> {
	List<Jornada> findByUsuario(Usuario usuario);
}
