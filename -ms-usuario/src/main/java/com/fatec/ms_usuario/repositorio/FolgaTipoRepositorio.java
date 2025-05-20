package com.fatec.ms_usuario.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatec.ms_usuario.entidade.Folga;
import com.fatec.ms_usuario.entidade.FolgaTipo;
import com.fatec.ms_usuario.entidade.Usuario;

@Repository
public interface FolgaTipoRepositorio extends JpaRepository<FolgaTipo, Long> {
	boolean existsByTipoFolgaNome(String tipoFolgaNome);
}