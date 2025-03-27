package com.fatec.ms_usuario.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.ms_usuario.entidade.Usuario;


public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

}