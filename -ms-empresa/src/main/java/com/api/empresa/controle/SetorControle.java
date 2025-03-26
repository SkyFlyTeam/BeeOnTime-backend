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

    @GetMapping("/{setorCod}")
    public ResponseEntity<Setor> obterSetorPorId(@PathVariable Long setorCod) {
        return repositorio.findById(setorCod)
                .map(setor -> new ResponseEntity<>(setor, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
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