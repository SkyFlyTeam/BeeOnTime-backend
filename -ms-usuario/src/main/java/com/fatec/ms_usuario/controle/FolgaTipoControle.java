package com.fatec.ms_usuario.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fatec.ms_usuario.entidade.FolgaTipo;
import com.fatec.ms_usuario.repositorio.FolgaTipoRepositorio;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/folga-tipos")
public class FolgaTipoControle {

    @Autowired
    private FolgaTipoRepositorio folgaTipoRepositorio;

    @GetMapping
    public List<FolgaTipo> listarTodas() {
        return folgaTipoRepositorio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FolgaTipo> buscarPorId(@PathVariable long id) {
        Optional<FolgaTipo> folgaTipo = folgaTipoRepositorio.findById(id);
        return folgaTipo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FolgaTipo> salvar(@RequestBody FolgaTipo folgaTipo) {
        FolgaTipo folgaTipoSalvo = folgaTipoRepositorio.save(folgaTipo);
        return ResponseEntity.status(201).body(folgaTipoSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id) {
        folgaTipoRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
