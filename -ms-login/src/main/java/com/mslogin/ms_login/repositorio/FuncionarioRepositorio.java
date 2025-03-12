package com.mslogin.ms_login.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mslogin.ms_login.entidade.Funcionario;

public interface FuncionarioRepositorio extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByFuncEmail(String funcEmail); // ✅ Correct method name
}


