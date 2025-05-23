package com.fatec.ms_usuario.controle;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.ms_usuario.entidade.Setor;
import com.fatec.ms_usuario.entidade.Usuario;
import com.fatec.ms_usuario.repositorio.SetorRepositorio;
import com.fatec.ms_usuario.repositorio.UsuarioRepositorio;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/setor") // Base URL para os endpoints da empresa
public class SetorControle {

    @Autowired
    private SetorRepositorio repositorio;

    @Autowired
    private UsuarioRepositorio usuario_repositorio;

    @GetMapping
    public ResponseEntity<List<Setor>> obterTodosSetores() {
        List<Setor> setores = repositorio.findAll();
        return new ResponseEntity<>(setores, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Setor> cadastrarSetor(@RequestBody Setor setor) {
        Setor savedSetor = repositorio.save(setor);
        return new ResponseEntity<>(savedSetor, HttpStatus.CREATED);
    }

    @GetMapping("/{setorCod}")
    public ResponseEntity<Setor> obterSetorPorId(@PathVariable Long setorCod) {
        return repositorio.findById(setorCod)
                .map(setor -> new ResponseEntity<>(setor, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/empresa/{empCod}")
    public ResponseEntity<List<Setor>> obterSetorPorEmpresa(@PathVariable Long empCod) {
        List<Setor> setores = repositorio.findByEmpCod(empCod);
        if (setores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(setores, HttpStatus.OK);
    }

    @GetMapping("/{setorCod}/usuarios")
    public ResponseEntity<?> getUsuariosBySetorCod(@PathVariable Long setorCod) {
        List<Usuario> usuarios = usuario_repositorio.findBySetorCod(setorCod);
        if(usuarios.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @PutMapping("/{setorCod}")
    public ResponseEntity<Setor> editarSetor(@PathVariable Long setorCod, @RequestBody Setor empresaAtualizada) {
        return repositorio.findById(setorCod)
                .map(setor -> {
                    setor.setSetorNome(empresaAtualizada.getSetorNome());
                    Setor updatedSetor = repositorio.save(setor);
                    return new ResponseEntity<>(updatedSetor, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{setorCod}")
    public ResponseEntity<Void> deletarSetor(@PathVariable Long setorCod) {
        if (repositorio.existsById(setorCod)) {
            repositorio.deleteById(setorCod);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
