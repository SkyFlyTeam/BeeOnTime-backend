package com.mslogin.ms_login.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mslogin.ms_login.entidade.Funcionario;

public interface FuncionarioRepositorio extends JpaRepository<Funcionario, Long> {

}
