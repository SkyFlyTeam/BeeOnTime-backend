package com.fatec.ms_usuario.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.ms_usuario.entidade.Usuario;


public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsuarioEmail(String funcEmail); // ✅ Correct method name

    // Método para verificar se o CPF já existe
    Optional<Usuario> findByUsuarioCpf(String usuario_cpf);

    // Método para verificar se o número de registro já existe
    Optional<Usuario> findByUsuarioNrRegistro(Double usuario_nrRegistro);

}