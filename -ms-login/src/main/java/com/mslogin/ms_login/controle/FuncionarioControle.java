package com.mslogin.ms_login.controle;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.mslogin.ms_login.entidade.Funcionario;
import com.mslogin.ms_login.repositorio.FuncionarioRepositorio;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/funcionario") // This sets a base URL for all endpoints in this controller
public class FuncionarioControle {
    @Autowired
    private FuncionarioRepositorio repositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<Funcionario> obterFuncionario() {
        return repositorio.findAll();
    }

    @PostMapping
    public void cadastrarFuncionario(@RequestBody Funcionario funcionario) {

        funcionario.setFuncSenha(passwordEncoder.encode(funcionario.getFuncSenha()));
        repositorio.save(funcionario);
    }

    @GetMapping("/{id}")
    public Funcionario obterFuncionarioId(@PathVariable Long id) {
        return repositorio.findById(id).orElseThrow(() -> new RuntimeException("Funcionario not found"));
    }

    public Optional<Funcionario> obterFuncionarioEmail(String email) {
       return repositorio.findByFuncEmail(email);
    }

    @DeleteMapping
    public void deletarFuncionario(@RequestBody Funcionario funcionario) {
        repositorio.deleteById(funcionario.getFunc_id());
    }
}

