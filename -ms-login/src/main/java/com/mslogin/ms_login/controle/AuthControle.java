package com.mslogin.ms_login.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.mslogin.ms_login.entidade.Funcionario;
import com.mslogin.ms_login.seguranca.JwtUtil;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthControle {

    @Autowired
    private FuncionarioControle funcionarioControle;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Funcionario funcionario) {
        Optional<Funcionario> funcionarioEncontrado = funcionarioControle.obterFuncionarioEmail(funcionario.getFuncEmail());

        if (funcionarioEncontrado.isPresent() && passwordEncoder.matches(funcionario.getFuncSenha(), funcionarioEncontrado.get().getFuncSenha())) {
            String token = jwtUtil.generateToken(funcionario.getFuncEmail());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }

    //
    //
    //
    @GetMapping("/{email}")
    public ResponseEntity<?> verificarAcesso(@PathVariable String email) {
        Optional<Funcionario> funcionarioEncontrado = funcionarioControle.obterFuncionarioEmail(email);
        
        if(!funcionarioEncontrado.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
        long acesso = funcionarioEncontrado.get().getFuncAcesso();
        return new ResponseEntity<>(acesso, HttpStatus.OK);
    }
}