package com.fatec.ms_usuario.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.fatec.ms_usuario.entidade.Usuario;
import com.fatec.ms_usuario.seguranca.JwtUtil;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthControle {

    @Autowired
    private UsuarioControle funcionarioControle;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario funcionario) {
        Optional<Usuario> funcionarioEncontrado = funcionarioControle.obterFuncionarioEmail(funcionario.getUsuarioEmail());

        if (funcionarioEncontrado.isPresent() && passwordEncoder.matches(funcionario.getUsuario_senha(), funcionarioEncontrado.get().getUsuario_senha())) {
            String token = jwtUtil.generateToken(funcionario.getUsuarioEmail());
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
        Optional<Usuario> funcionarioEncontrado = funcionarioControle.obterFuncionarioEmail(email);
        
        if(!funcionarioEncontrado.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
        long acesso = funcionarioEncontrado.get().getNivelAcesso_cod();
        return new ResponseEntity<>(acesso, HttpStatus.OK);
    }
}
