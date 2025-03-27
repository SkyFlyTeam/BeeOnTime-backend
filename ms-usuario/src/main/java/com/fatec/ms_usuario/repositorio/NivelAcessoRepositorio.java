package com.fatec.ms_usuario.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.ms_usuario.entidade.NivelAcesso;

public interface NivelAcessoRepositorio extends JpaRepository<NivelAcesso, Long> {

}
