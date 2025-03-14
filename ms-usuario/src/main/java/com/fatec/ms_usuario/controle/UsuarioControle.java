package com.fatec.ms_usuario.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.ms_usuario.entidade.Usuario;
import com.fatec.ms_usuario.repositorio.UsuarioRepositorio;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class UsuarioControle {

    @Autowired
    private UsuarioRepositorio repositorio;

    @GetMapping("/")
    public List<Usuario> obterUsuarios() {
        return repositorio.findAll();
    }

    @PostMapping("/cadastrar")
    public void cadastrarUsuario(@RequestBody Usuario alvo) {
        repositorio.save(alvo);
    }


    @GetMapping("/buscar-id/{id}")
    public Usuario obterUsuarioId(@PathVariable Long id) {
        return repositorio.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    @PutMapping("/atualizar-id")
    public void atualizarUsuario(@RequestBody Usuario usuario) {
        Usuario alvo = repositorio.findById(usuario.getUsuario_cod()).get();

        alvo.setUsuario_nome(usuario.getUsuario_nome());

        alvo.setUsuario_cpf(usuario.getUsuario_cpf());

        alvo.setUsuario_nRegistro(usuario.getUsuario_nRegistro());
        
        alvo.setUsuario_cargaHoraria(usuario.getUsuario_cargaHoraria());
        
        alvo.setUsuario_contratacao(usuario.getUsuario_contratacao());
        
        alvo.setUsuario_dataContratacao(usuario.getUsuario_dataContratacao());
        
        alvo.setUsuario_senha(usuario.getUsuario_senha());
        
        alvo.setUsuario_email(usuario.getUsuario_email());
        
        alvo.setUsuario_dataNascimento(usuario.getUsuario_dataNascimento());

        repositorio.save(alvo);
    }
    
    
    @DeleteMapping("/{id}")
    public void deletarUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario alvo = repositorio.findById(usuario.getUsuario_cod()).get();
            repositorio.delete(alvo);
        } finally {System.err.println("500 error u not found");}
    }
}
