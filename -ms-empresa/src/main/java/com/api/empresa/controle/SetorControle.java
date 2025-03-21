package com.api.empresa.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.empresa.entidade.Setor;
import com.api.empresa.repositorio.SetorRepositorio;

@RestController
@RequestMapping("/setor") // Base URL para os endpoints da empresa
public class SetorControle {

    @Autowired
    private SetorRepositorio repositorio;

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

    @GetMapping("/{setor_cod}")
    public ResponseEntity<Setor> obterSetorPorId(@PathVariable Long setor_cod) {
        return repositorio.findById(setor_cod)
                .map(setor -> new ResponseEntity<>(setor, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{setor_cod}")
    public ResponseEntity<Setor> editarSetor(@PathVariable Long setor_cod, @RequestBody Setor empresaAtualizada) {
        return repositorio.findById(setor_cod)
                .map(setor -> {
                    setor.setSetor_nome(empresaAtualizada.getSetor_nome());
                    Setor updatedSetor = repositorio.save(setor);
                    return new ResponseEntity<>(updatedSetor, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{setor_cod}")
    public ResponseEntity<Void> deletarSetor(@PathVariable Long setor_cod) {
        if (repositorio.existsById(setor_cod)) {
            repositorio.deleteById(setor_cod);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}