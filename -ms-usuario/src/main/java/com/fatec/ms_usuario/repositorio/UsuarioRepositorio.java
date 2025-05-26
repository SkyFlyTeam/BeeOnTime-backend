package com.fatec.ms_usuario.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.ms_usuario.entidade.Usuario;
import java.util.List;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsuarioEmail(String funcEmail); // ✅ Correct method name
    List<Usuario> findBySetorCod(Long setorCod);
    List<Usuario> findByEmpCod(Long empCod);
}