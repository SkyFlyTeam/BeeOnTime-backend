package com.fatec.ms_usuario.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fatec.ms_usuario.entidade.Folga;
import com.fatec.ms_usuario.entidade.FolgaTipo;
import com.fatec.ms_usuario.entidade.Usuario;
import com.fatec.ms_usuario.repositorio.FolgaRepositorio;
import com.fatec.ms_usuario.repositorio.FolgaTipoRepositorio;
import com.fatec.ms_usuario.repositorio.UsuarioRepositorio;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/usuario/folgas")
public class FolgaControle {

    @Autowired
    private FolgaRepositorio folgaRepositorio;
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Autowired
    private FolgaTipoRepositorio folgaTipoRepositorio;

    @GetMapping
    public List<Folga> listarTodas() {
        return folgaRepositorio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Folga> buscarPorId(@PathVariable long id) {
        Optional<Folga> folga = folgaRepositorio.findById(id);
        return folga.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Folga> salvar(@RequestBody Folga folga) {
        // Buscar o Usuario pelo código
        Usuario usuario = usuarioRepositorio.findById(folga.getUsuario().getUsuario_cod()).orElse(null);  // Acessando o usuario_cod de Usuario

        // Buscar o FolgaTipo pelo código
        FolgaTipo folgaTipo = folgaTipoRepositorio.findById(folga.getFolgaTipo().getFolTipoCod()).orElse(null);

        // Verificar se as entidades foram encontradas
        if (usuario != null && folgaTipo != null) {
            // Associar o Usuario e o FolgaTipo à Folga
            folga.setUsuario(usuario);  // Atribuindo o objeto Usuario à Folga
            folga.setFolgaTipo(folgaTipo); // Associando o FolgaTipo à Folga

            // Salvar a Folga, o folCod será gerado automaticamente pelo banco de dados
            Folga folgaSalva = folgaRepositorio.save(folga);

            return ResponseEntity.status(201).body(folgaSalva);
        } else {
            return ResponseEntity.badRequest().build();  // Se não encontrar o usuário ou o tipo de folga
        }
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id) {
        folgaRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
